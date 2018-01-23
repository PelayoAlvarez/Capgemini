package com.capgemini.piloto.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.repository.CuentaRepository;

@RestController
@RequestMapping(path = "/cuenta")
public class CuentaController {

	@Autowired
	private CuentaRepository cuentaRepository;

	// Get All Notes
	@GetMapping("/cuenta")
	public List<Cuenta> getAllCuentas() {
		return cuentaRepository.findAll();
	}

	// Create a new Note
	@PostMapping("/cuenta")
	public Cuenta createCuenta(@Valid @RequestBody Cuenta cuenta) {
		return cuentaRepository.save(cuenta);
	}

	// Get a Single Note
	@GetMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "id") Long cuentaId) {
		Cuenta cuenta = cuentaRepository.findOne(cuentaId);
		if (cuenta == null || !cuenta.getMCA_Habilitado()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cuenta);
	}

	// Update a Note
	@PutMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> updateCuenta(@PathVariable(value = "id") Long cuentaId,
			@Valid @RequestBody Cuenta cuentaDetails) {
		Cuenta cuenta = cuentaRepository.findOne(cuentaId);
		if (cuenta == null || !cuenta.getMCA_Habilitado()) {
			return ResponseEntity.notFound().build();
		}
		cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());

		Cuenta updateCuenta = cuentaRepository.save(cuenta);
		return ResponseEntity.ok(updateCuenta);
	}

	// Delete a Note
	@DeleteMapping("/cuenta/{id}")
	public ResponseEntity<Cuenta> deleteCuenta(@PathVariable(value = "id") Long cuentaId) {
		Cuenta cuenta = cuentaRepository.findOne(cuentaId);
		if (cuenta == null || !cuenta.getMCA_Habilitado()) {
			return ResponseEntity.notFound().build();
		}

		cuenta.setMCA_Habilitado(false);
		cuentaRepository.save(cuenta);
		return ResponseEntity.ok().build();
	}
}
