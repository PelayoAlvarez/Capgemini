package com.capgemini.piloto.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Association;
import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.dto.CuentaDTO;
import com.capgemini.piloto.model.historico.CuentaH;
import com.capgemini.piloto.repository.ClienteCuentaRepository;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.historico.CuentaHRepository;

@RestController
@RequestMapping(path = "/cuenta")
public class CuentaController {

	private static final String NOT_FOUND = "The requested account was not found";

	private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);
	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private CuentaHRepository cuentaHRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteCuentaRepository clienteCuentaRepository;
	
	// Get every account
	@GetMapping("/cuenta")
	public List<Cuenta> getAllCuentas() {
		logger.info("Requested every active account");
		return cuentaRepository.findMCA();
	}

	// Create a new account
	@PostMapping("/cuenta")
	public ResponseEntity<Cuenta> createCuenta(@RequestBody CuentaDTO cuentadto,
			@RequestParam String dni) {
		Cuenta aux1 = cuentaRepository.findByNumeroCuenta(cuentadto.getNumeroCuenta());
		if (aux1 != null) {
			logger.info("An account with the given numeroCuenta already existed", aux1.getNumeroCuenta());
			return new ResponseEntity<>(aux1, new HttpHeaders(), HttpStatus.CONFLICT);
		}
		Cuenta cuenta = new Cuenta(cuentadto); 
		logger.info("Created a new account");
		Cliente aux = clienteRepository.findByDni(dni);
		if (aux != null) {
			cuenta.setFecCreacion(new Date());
			ClienteCuenta cc = new ClienteCuenta(aux,cuenta);
			clienteCuentaRepository.save(cc);
			cuentaRepository.save(cuenta);
			return ResponseEntity.ok().body(cuenta);
		}

		return ResponseEntity.notFound().build();
	}

	// Find an account by its id
	@GetMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "id") String numeroCuenta) {
		Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
		if (cuenta == null || !cuenta.getmCAHabilitado()) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		logger.info("The requested account was found");
		return ResponseEntity.ok().body(cuenta);
	}

	// Delete an account by its id
	@DeleteMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> deleteCuenta(@PathVariable(value = "id") String numeroCuenta) {
		Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
		if (cuenta == null || !cuenta.getmCAHabilitado()) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}

		if (!cuenta.getClienteCuenta().isEmpty()) {
			for (ClienteCuenta c : cuenta.getClienteCuenta()) {
				c.unlinkCuenta();
			}
		}

		cuenta.setmCAHabilitado(false);
		cuentaRepository.save(cuenta);
		logger.info("The account was successfully deleted");
		return ResponseEntity.ok().build();
	}
}
