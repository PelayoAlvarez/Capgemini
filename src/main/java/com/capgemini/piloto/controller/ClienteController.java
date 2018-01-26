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

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.historico.ClienteH;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.SucursalRepository;
import com.capgemini.piloto.repository.historico.ClienteHRepository;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String NOT_FOUND = "The requested client was not found";
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	
	@Autowired
	private ClienteHRepository clienteHRepository;
	
	@Autowired
	SucursalRepository sucursalRepository;
	
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
	public ResponseEntity<Cliente> createClient(@RequestBody Cliente cliente) {
		Cliente cliente1 = clienteRepository.findByDni(cliente.getDni());
		if(cliente1 != null) {
			logger.error("The client is already created");
			return new ResponseEntity<>(cliente1, new HttpHeaders(), HttpStatus.CONFLICT);
			
		}	
		Sucursal aux = sucursalRepository.findById(cliente.getSucursal().getId());
		cliente.setSucursal(aux);
		aux.getClientes().add(cliente);
		Cliente c2 = clienteRepository.save(cliente);
		if(c2 == null) {
			logger.error("The client was not created");
			return new ResponseEntity<>(c2, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("Create a new client");
		return ResponseEntity.ok().body(c2);
		
	}
	
	// Find a client by its dni
	@GetMapping("/clientes/{dni}")
	public ResponseEntity<Cliente> getClientByDni(@PathVariable(value ="dni") String dni){
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null || !cliente.getmCAHabilitado()) {
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
		if(cliente == null || !cliente.getmCAHabilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		
		//Cogemos como prueba el usuario de la entidad
		clienteHRepository.save(new ClienteH(cliente,cliente.getEmpleado()));
		cliente.setNombre(detailsClient.getNombre());
		cliente.setApellidos(detailsClient.getApellidos());
		cliente.setDireccion(detailsClient.getDireccion());
		cliente.setMovil(detailsClient.getMovil());
		cliente.setFijo(detailsClient.getFijo());
		cliente.setFecActu(new Date());

		Cliente updateClient = clienteRepository.save(cliente);
		logger.info("The client was successfully updated");
		return ResponseEntity.ok(updateClient);
	}
	
	// Delete a cliente by its dni
	@DeleteMapping("/cliente/{dni}")
	public ResponseEntity<Cliente> deleteNote(@PathVariable(value = "dni") String dni) {
		Cliente cliente = clienteRepository.findByDni(dni);
		if(cliente == null || !cliente.getmCAHabilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		clienteHRepository.save(new ClienteH(cliente, cliente.getEmpleado()));
		//Parte de Alperi para desvincular las cuentas de los clientes en caso de que no haya mas clientes 
		//asociados a esas cuentas eliminar la cuenta
		//unlink(cliente);
		for (ClienteCuenta cc : cliente.getClienteCuentas()){
				cc.unlinkCliente();
		}
		cliente.setmCAHabilitado(false);
		clienteRepository.save(cliente);
		
		logger.info("The client was successfully deleted");
	
		return ResponseEntity.ok().build();
	}

}
