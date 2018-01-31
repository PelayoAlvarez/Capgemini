package com.capgemini.piloto.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Validation;
import com.capgemini.piloto.model.dto.ClienteTitularDTO;
import com.capgemini.piloto.model.dto.CuentaDTO;
import com.capgemini.piloto.model.dto.MisCuentasDTO;
import com.capgemini.piloto.repository.ClienteCuentaRepository;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.CuentaRepository;

@RestController
@RequestMapping(path = "/cuenta")
public class CuentaController {

	private static final String NOT_FOUND = "The requested account was not found";

	private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);
	@Autowired
	private CuentaRepository cuentaRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ClienteCuentaRepository clienteCuentaRepository;

	// Get every account
	@GetMapping("/cuenta")
	public List<CuentaDTO> getAllCuentas() {
		logger.info("Requested every active account");
		List<Cuenta> aux = cuentaRepository.findMCA();
		List<CuentaDTO> aux2 = new ArrayList<>();
		for (Cuenta c : aux)
			aux2.add(new CuentaDTO(c));
		return aux2;
	}

	// Get every account and its owners with X dni
	@GetMapping("/miscuentas/{dni}")
	public ResponseEntity<List<MisCuentasDTO>> getMisCuentas(@PathVariable(value = "dni") String dni) {
		if(!Validation.dniValido(dni)){
			logger.info("The value of dni was not valid");
			return new ResponseEntity<>(new ArrayList<>(), new HttpHeaders(), HttpStatus.CONFLICT);
		}
		logger.info("FIND: Se obtienen las cuentas del cliente con DNI [{}]", dni);
		Cliente cliente = clienteRepository.findByDni(dni);
		if (cliente == null) {
			logger.info("GET: No se encuentra el cliente con el DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		List<ClienteCuenta> cuentas = clienteCuentaRepository.findByDni(dni);
		List<MisCuentasDTO> misCuentas = new ArrayList<>();
		for (ClienteCuenta cuenta : cuentas) {
			List<ClienteTitularDTO> titulares = new ArrayList<>();
			List<ClienteCuenta> clientes = clienteCuentaRepository
					.findByNumeroCuenta(cuenta.getCuenta().getNumeroCuenta());
			clientes.forEach(titular -> titulares
					.add(new ClienteTitularDTO(clienteRepository.findByDni(titular.getCliente().getDni()))));
			misCuentas.add(new MisCuentasDTO(cuenta.getCuenta().getNumeroCuenta(), titulares));
		}
		return ResponseEntity.ok(misCuentas);
	}

	// Create a new account
	@PostMapping("/cuenta")
	public ResponseEntity<Cuenta> createCuenta(@RequestBody CuentaDTO cuentadto, @RequestParam String dni) {
		if(!Validation.dniValido(dni)){
			logger.info("The value of dni was not valid");
			return new ResponseEntity<>(new Cuenta(cuentadto), new HttpHeaders(), HttpStatus.CONFLICT);
		}
		Cuenta aux1 = cuentaRepository.findByNumeroCuenta(cuentadto.getNumeroCuenta());
		if (aux1 != null) {
			logger.info("An account with the given numeroCuenta already existed", aux1.getNumeroCuenta());
			return new ResponseEntity<>(aux1, new HttpHeaders(), HttpStatus.CONFLICT);
		}
		Cuenta cuenta = new Cuenta(cuentadto);
		logger.info("Created a new account with numeroCuenta = " 
				+ cuenta.getNumeroCuenta());
		Cliente aux = clienteRepository.findByDni(dni);
		if (aux != null) {
			cuenta.setFecCreacion(new Date());
			ClienteCuenta cc = new ClienteCuenta(aux, cuenta);
			clienteCuentaRepository.save(cc);
			cuentaRepository.save(cuenta);
			return ResponseEntity.ok().body(cuenta);
		}

		return ResponseEntity.notFound().build();
	}

	// Delete an account by its numeroCuenta
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
