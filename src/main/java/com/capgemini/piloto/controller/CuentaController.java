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
	
	//Get a list with every account
		@GetMapping("/cuenta")
		public List<Cuenta> getAllCuentas() {
			return cuentaRepository.findAll();
		}
		
		//Create a new account
		@PostMapping("/cuenta")
		public Cuenta createCuenta(@Valid @RequestBody Cuenta cuenta) {
			return cuentaRepository.save(cuenta);		
		}
		
		//Get an account with the specified id
		@GetMapping("/cuenta/{id}")
		public ResponseEntity<Cuenta> getCuentaById(@PathVariable(value = "id") Long cuentaId) {
			Cuenta cuenta = cuentaRepository.findOne(cuentaId);
			if(cuenta == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(cuenta);
		}
		
		//Update an account
		@PutMapping("/cuenta/{id}")
		public ResponseEntity<Cuenta> updateCuenta(@PathVariable(value = "id") Long cuentaId,
				@Valid @RequestBody Cuenta cuentaDetails) {
			Cuenta cuenta = cuentaRepository.findOne(cuentaId);
			if(cuenta == null) {
				return ResponseEntity.notFound().build();
			}
			cuenta.setNumeroCuenta(cuentaDetails.getNumeroCuenta());
			
			Cuenta updateCuenta = cuentaRepository.save(cuenta);
			return ResponseEntity.ok(updateCuenta);
		}
		
		// Delete an account
		@DeleteMapping("/cuenta/{id}")
		public ResponseEntity<Cuenta> deleteCuenta(@PathVariable(value = "id") Long cuentaId) {
			Cuenta cuenta = cuentaRepository.findOne(cuentaId);
			if(cuenta == null) {
				return ResponseEntity.notFound().build();
			}
			
			cuentaRepository.delete(cuenta);
			return ResponseEntity.ok().build();
		}
}
