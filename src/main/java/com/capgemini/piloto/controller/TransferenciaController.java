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

import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.repository.TransferenciaRepository;

@RestController
@RequestMapping(path = "/transferencia")
public class TransferenciaController {

	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	//Get All Notes
		@GetMapping("/transferencia")
		public List<Transferencia> getAllTransferencias() {
			return transferenciaRepository.findAll();
		}
		
		//Create a new Note
		@PostMapping("/trasnferencia")
		public Transferencia createCuenta(@Valid @RequestBody Transferencia transferencia) {
			return transferenciaRepository.save(transferencia);		
		}
		
		//Get a Single Note
		@GetMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> getTransferenciaById(@PathVariable(value = "id") Long transferenciaId) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.ok().body(transferencia);
		}
		
		//Update a Note
		@PutMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> updateTransferencia(@PathVariable(value = "id") Long transferenciaId,
				@Valid @RequestBody Transferencia transferenciaDetails) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null) {
				return ResponseEntity.notFound().build();
			}
			transferencia.setCanal(transferenciaDetails.getCanal());
			transferencia.setFecha_consolidacion(transferenciaDetails.getFecha_consolidacion());
			transferencia.setFecha_transferencia(transferenciaDetails.getFecha_transferencia());
			transferencia.setId_destino(transferencia.getId_destino());
			transferencia.setId_origen(transferencia.getId_origen());
			transferencia.setImporte(transferencia.getImporte());
			
			Transferencia updateTransferencia = transferenciaRepository.save(transferencia);
			return ResponseEntity.ok(updateTransferencia);
		}
		
		// Delete a Note
		@DeleteMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> deleteTransferencia(@PathVariable(value = "id") Long transferenciaId) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null) {
				return ResponseEntity.notFound().build();
			}
			
			transferenciaRepository.delete(transferencia);
			return ResponseEntity.ok().build();
		}
}
