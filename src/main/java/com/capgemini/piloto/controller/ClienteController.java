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

import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.model.Cliente;

@RestController
@RequestMapping("/")
public class ClienteController {
	
	@Autowired
	ClienteRepository clienteRepository;
	
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes(){
		return clienteRepository.findAll();	
	}
	
	@PostMapping("/clientes")
	public Cliente createClient(@Valid @RequestBody Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}
	
	@GetMapping("/clientes/{dni}")
	public ResponseEntity<Cliente> getClientByDni(@PathVariable(value ="dni") String dni){
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(cliente);
	}
	
	@PutMapping("/cliente/{dni}")
	public ResponseEntity<Cliente> updateNote(@PathVariable(value = "dni") String dni,
			@Valid @RequestBody Cliente detailsClient) {
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		cliente.setNombre(detailsClient.getNombre());
		cliente.setApellidos(detailsClient.getApellidos());
		cliente.setDireccion(detailsClient.getDireccion());
		cliente.setMovil(detailsClient.getMovil());
		cliente.setFijo(detailsClient.getFijo());

		Cliente updateClient = clienteRepository.save(cliente);
		return ResponseEntity.ok(updateClient);
	}
	
	@DeleteMapping("/cliente/{dni}")
	public ResponseEntity<Cliente> deleteNote(@PathVariable(value = "dni") String dni) {
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null) {
			return ResponseEntity.notFound().build();
		}
		
		cliente.setMCA_Habilitado(false);
		clienteRepository.save(cliente);
	
		return ResponseEntity.ok().build();
	}

}
