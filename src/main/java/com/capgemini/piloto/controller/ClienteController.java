package com.capgemini.piloto.controller;


import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.capgemini.piloto.repository.historico.ClienteRepositoryH;
import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.historico.ClienteH;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String NOT_FOUND = "The requested client was not found";
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	
	@Autowired
	private ClienteRepositoryH clienteHRepository;
	
	@Autowired
	ClienteRepository clienteRepository;
	
	// Get every client
	@GetMapping("/clientes")
	public List<Cliente> getAllClientes(){
		logger.info("Requested evey active client");
		return clienteRepository.findMCA();	
	}
	
	// Create a new client
	@PostMapping("/clientes")
	public Cliente createClient(@Valid @RequestBody Cliente cliente) {
		clienteHRepository.save(new ClienteH(cliente));
		logger.info("Create a new client");
		return clienteRepository.save(cliente);
		
	}
	
	// Find a client by its dni
	@GetMapping("/clientes/{dni}")
	public ResponseEntity<Cliente> getClientByDni(@PathVariable(value ="dni") String dni){
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null || !cliente.getMCA_Habilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		logger.info("The requested cliente vas found");
		return ResponseEntity.ok().body(cliente);
	}
	
	// Update a client
	@PutMapping("/cliente/{dni}")
	public ResponseEntity<Cliente> updateNote(@PathVariable(value = "dni") String dni,
			@Valid @RequestBody Cliente detailsClient) {
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null || !cliente.getMCA_Habilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		clienteHRepository.save(new ClienteH(cliente));
		cliente.setNombre(detailsClient.getNombre());
		cliente.setApellidos(detailsClient.getApellidos());
		cliente.setDireccion(detailsClient.getDireccion());
		cliente.setMovil(detailsClient.getMovil());
		cliente.setFijo(detailsClient.getFijo());
		cliente.setFecha_Actua(new Date());

		Cliente updateClient = clienteRepository.save(cliente);
		logger.info("The client was successfullu updated");
		return ResponseEntity.ok(updateClient);
	}
	
	// Delete a cliente by its dni
	@DeleteMapping("/cliente/{dni}")
	public ResponseEntity<Cliente> deleteNote(@PathVariable(value = "dni") String dni) {
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null || !cliente.getMCA_Habilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		clienteHRepository.save(new ClienteH(cliente));
		
		cliente.setMCA_Habilitado(false);
		clienteRepository.save(cliente);
		
		logger.info("The client was successfullu deleted");
	
		return ResponseEntity.ok().build();
	}

}
