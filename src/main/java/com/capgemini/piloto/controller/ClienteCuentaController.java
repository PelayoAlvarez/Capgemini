package com.capgemini.piloto.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.dto.GestionTitularesCuentaDTO;
import com.capgemini.piloto.model.historico.ClienteCuentaH;
import com.capgemini.piloto.model.types.ClienteCuentaKey;
import com.capgemini.piloto.repository.ClienteCuentaRepository;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.historico.ClienteCuentaHRepository;

@RestController
@RequestMapping("/cliente_cuenta")
public class ClienteCuentaController {
	
	//Logger
	private Logger log = LoggerFactory.getLogger(getClass());

	// Repositorios
	@Autowired
	ClienteCuentaRepository clienteCuentaRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	CuentaRepository cuentaRepository;

	// Repositorios de historicos
	@Autowired
	ClienteCuentaHRepository clienteCuentaHRepository;

	
	// Creacion de una nueva asociacion entre una cuenta y un cliente.
	@PutMapping("/asociar")
	public ResponseEntity<GestionTitularesCuentaDTO> createNote(@RequestBody GestionTitularesCuentaDTO datos) {
		Cuenta cuenta = cuentaRepository.findOne(datos.getNumeroCuenta());
		if (cuenta == null) {
			return ResponseEntity.notFound().build();
		}

		if (datos.getDniTitulares().isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		
		Set<Cliente> clientes = new HashSet<>();
		for (String dniTitular : datos.getDniTitulares()) {
			Cliente cliente = clienteRepository.findByDni(dniTitular);
			clientes.add(cliente);
			if (cliente == null) {
				return ResponseEntity.notFound().build();
			}
		}

		Set<ClienteCuenta> clientesCuentas = cuenta._getClienteCuenta();
		for (ClienteCuenta cc : clientesCuentas) {
			if (clientes.contains(cc.getCliente())) {
				if (!cc.getMcaHabilitado()) {
					// estaba borrado logico --> lo habilito de nuevo en la tabla ClienteCuenta
					clienteCuentaHRepository.save(new ClienteCuentaH(cc, cc.getUsuario()));
					cc.setMcaHabilitado(true);
					cc.setFecActu(new Date());
					log.info("PUT: Se crea la relación de titular entre la cuenta con nº[{}] y con el cliente con DNI[{}]",
							datos.getNumeroCuenta(),cc.getCliente().getDni());
					clienteCuentaRepository.save(cc);
				}
				clientes.remove(cc.getCliente());
			} else {
				// el cliente ya no esta en la lista de clientes titulares --> hay que borrarlo logicamente en la tabla ClienteCuenta
				clienteCuentaHRepository.save(new ClienteCuentaH(cc, cc.getUsuario()));
				cc.setMcaHabilitado(false);
				cc.setFecActu(new Date());
				log.info("PUT: Se borra la relación de titular entre la cuenta con nº[{}] y con el cliente con DNI[{}]",
						datos.getNumeroCuenta(),cc.getCliente().getDni());
				clienteCuentaRepository.save(cc);
			}		
		}

		// Son clientes que nunca han tenido antes una relacion con la cuenta que recibimos
		for (Cliente cliente : clientes) {
			clienteCuentaRepository.save(new ClienteCuenta(cliente, cuenta));
			log.info("PUT: Se crea la relación de titular entre la cuenta con nº[{}] y con el cliente con DNI[{}]",
					datos.getNumeroCuenta(),cliente.getDni());
		}

		return ResponseEntity.ok().build();
	}

	// Obtencion de una asociciacion entre una cuenta y un cliente
	@GetMapping("/asociar")
	public ResponseEntity<GestionTitularesCuentaDTO> getNoteById(@RequestParam(value = "dni") String dni,
			@RequestParam(value = "numero_cuenta") String numCuenta) {
		ClienteCuentaKey ccK = new ClienteCuentaKey(dni, numCuenta);
		ClienteCuenta cc = clienteCuentaRepository.findOne(ccK);
		if (cc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(new GestionTitularesCuentaDTO(cc));
	}

	// Eliminar asociacion entre cliente y cuenta
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<GestionTitularesCuentaDTO> deleteNote(@RequestParam(value = "dni") String dni,
			@RequestParam(value = "numero_cuenta") String numCuenta) {
		ClienteCuentaKey ccK = new ClienteCuentaKey(dni, numCuenta);
		ClienteCuenta cc = clienteCuentaRepository.findOne(ccK);

		if (cc == null) {
			return ResponseEntity.notFound().build();
		}
		clienteCuentaHRepository.save(new ClienteCuentaH(cc, cc.getUsuario()));
		cc.setMcaHabilitado(false);
		cc.setFecActu(new Date());
		clienteCuentaRepository.save(cc);
		return ResponseEntity.ok(new GestionTitularesCuentaDTO(cc));
	}
}
