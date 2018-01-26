package com.capgemini.piloto.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.historico.ClienteCuentaH;
import com.capgemini.piloto.model.types.ClienteCuentaKey;
import com.capgemini.piloto.repository.ClienteCuentaRepository;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.historico.ClienteCuentaHRepository;

@RestController
@RequestMapping("/cliente_cuenta")
public class ClienteCuentaController {

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
	@PostMapping("/asociar")

	public ResponseEntity<ClienteCuenta> createNote(@RequestParam(value = "dni") String dni,
			@RequestParam(value = "numero_cuenta") String numCuenta) {
		Cuenta cuenta = cuentaRepository.findOne(numCuenta);
		if (cuenta == null) {
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteRepository.findByDni(dni);
		if (cliente == null) {
			return ResponseEntity.notFound().build();
		}

		ClienteCuenta cc = new ClienteCuenta(cliente, cuenta);
		cc = clienteCuentaRepository.save(cc);
		return ResponseEntity.ok(cc);
	}

	// Obtencion de una asociciacion entre una cuenta y un cliente
	@GetMapping("/asociar")
	public ResponseEntity<ClienteCuenta> getNoteById(@RequestParam(value = "dni") String dni,
			@RequestParam(value = "numero_cuenta") String numCuenta) {
		ClienteCuentaKey ccK = new ClienteCuentaKey(dni, numCuenta);
		ClienteCuenta cc = clienteCuentaRepository.findOne(ccK);

		if (cc == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cc);
	}

	// Eliminar asociacion entre cliente y cuenta
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<ClienteCuenta> deleteNote(@RequestParam(value = "dni") String dni,
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
		return ResponseEntity.ok(cc);
	}
}
