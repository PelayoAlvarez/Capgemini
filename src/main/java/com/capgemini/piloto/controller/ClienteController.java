package com.capgemini.piloto.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.data.export.ExportClientes;
import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;
import com.capgemini.piloto.errors.impl.TextoFormatException;
import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.dto.ClienteDTO;
import com.capgemini.piloto.model.historico.ClienteH;
import com.capgemini.piloto.repository.ClienteRepository;
import com.capgemini.piloto.repository.SucursalRepository;
import com.capgemini.piloto.repository.historico.ClienteHRepository;
import com.capgemini.piloto.util.validator.ComunValidator;
import com.capgemini.piloto.util.validator.PersonValidator;

@RestController
@RequestMapping("/clientes")
@CrossOrigin
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
	@GetMapping("/listarClientes")
	public List<ClienteDTO> getAllClientes() {
		logger.info("Requested evey active client");
		List<Cliente> clientes = clienteRepository.findMCA();
		List<ClienteDTO> clientesDTO = new ArrayList<>();
		for (Cliente cliente : clientes) {
			clientesDTO.add(new ClienteDTO(cliente));
		}
		return clientesDTO;
	}

	// Create a new client
	@PostMapping("/clientes")
	public ResponseEntity<ClienteDTO> createClient(@Valid @RequestBody ClienteDTO clienteDTO, @RequestParam Long sucursalId) {
		try {
			validarCliente(clienteDTO);
			Cliente cliente1 = clienteRepository.findByDni(clienteDTO.getDni());
			if (cliente1 != null && cliente1.getmCAHabilitado()) {
					logger.error("The client is already created");
					return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.CONFLICT);
			}
			Sucursal sucursal = sucursalRepository.findById(sucursalId);
			if (sucursal == null || !sucursal.getMcaHabilitado()) {
				logger.error("The sucursl was nor created");
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.CONFLICT);
		}
			clienteDTO.setSucursal(sucursalId);
			Cliente c2 = clienteRepository.save(new Cliente(clienteDTO, sucursal));
			if (c2 == null) {
				logger.error("The client was not created");
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			logger.info("Create a new client");
			return ResponseEntity.ok().body(new ClienteDTO(c2));	
		}catch(DniFormatException e) {
			logger.error("CheckDNI: "+e.getMessage());
		}
		catch(EmailFormatException e) {
			logger.error("CheckEmail: "+e.getMessage());
		}
		catch(TelefonoFormatException e) {
			logger.error("CheckTelefono: "+e.getMessage());
		}
		catch(TextoFormatException e) {
			logger.error("CheckText: "+e.getMessage());
		} 
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
		

	
	// Find a client by its dni
	@GetMapping("/buscarCliente/{dni}")
	public ResponseEntity<ClienteDTO> getClientByDni(@PathVariable(value = "dni") String dni) {
		try {
			PersonValidator.validateDni(dni);
		}catch(DniFormatException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cliente cliente = clienteRepository.findByDni(dni);
		if (cliente == null || !cliente.getmCAHabilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		logger.info("The requested cliente vas found");
		ClienteDTO clienteDTO = new ClienteDTO(cliente);
		return ResponseEntity.ok().body(clienteDTO);
	}

	// Update a client
	@PutMapping("/cliente/{dni}")
	public ResponseEntity<ClienteDTO> updateCliente(@PathVariable(value = "dni") String dni,
			@Valid @RequestBody ClienteDTO detailsClient) {
		try {
			
			validarCliente(detailsClient);
			Cliente cliente = clienteRepository.findByDni(dni);
			if (cliente == null || !cliente.getmCAHabilitado()) {
				logger.error(NOT_FOUND);
				return ResponseEntity.notFound().build();
			}

			// Cogemos como prueba el usuario de la entidad
			clienteHRepository.save(new ClienteH(cliente, cliente.getEmpleado()));
			Sucursal sucursal = sucursalRepository.findById(detailsClient.getSucursal());
			cliente = new Cliente(detailsClient,sucursal);

			Cliente updateClient = clienteRepository.save(cliente);
			logger.info("The client was successfully updated");
			return ResponseEntity.ok(new ClienteDTO(updateClient));
		}catch(DniFormatException e) {
			logger.error("CheckDNI: "+e.getMessage());
		}
		catch(EmailFormatException e) {
			logger.error("CheckEmail: "+e.getMessage());
		}
		catch(TelefonoFormatException e) {
			logger.error("CheckTelefonoFijo: "+e.getMessage());
		}
		catch(TextoFormatException e) {
			logger.error("CheckTelefonoMovil: "+e.getMessage());
		} 
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	
	}

	// Delete a cliente by its dni
	@DeleteMapping("/cliente/{dni}")
	public ResponseEntity<ClienteDTO> deleteCliente(@Valid @PathVariable(value = "dni") String dni) {
		try {
			PersonValidator.validateDni(dni);
		}catch(DniFormatException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cliente cliente = clienteRepository.findByDni(dni);
		if (cliente == null || !cliente.getmCAHabilitado()) {
			logger.error(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		clienteHRepository.save(new ClienteH(cliente, cliente.getEmpleado()));

		for (ClienteCuenta cc : cliente.getClienteCuentas()) {
			cc.unlinkCliente();
		}
		cliente.setmCAHabilitado(false);
		clienteRepository.save(cliente);

		logger.info("The client was successfully deleted");
		ClienteDTO clienteDTO = new ClienteDTO(cliente);

		return ResponseEntity.ok().body(clienteDTO);
	}
	
	@GetMapping("/export")
	public ResponseEntity<Empleado> exportEmpleados() {
		ExportClientes  export = new ExportClientes("prueba");
		logger.info("EXPORT: Se exportan los datos de los empleados");
		if(export.export(getAllClientes())) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
//---------------------------------------------------------------------------------------------------
	//Validar los campos del cliente
	private void validarCliente(ClienteDTO clienteDTO){
			PersonValidator.validateDni(clienteDTO.getDni());
			ComunValidator.validateTexto(clienteDTO.getDireccion(), "Direccion", 50);
			ComunValidator.validateTexto(clienteDTO.getNombre(), "Nombre", 15);
			ComunValidator.validateTexto(clienteDTO.getApellidos(), "Apellidos",30);
			if(!clienteDTO.getFijo().equals(""))
				PersonValidator.validateTelefonoFijo(clienteDTO.getFijo());
			if(!clienteDTO.getMovil().equals(""))
				PersonValidator.validateTelefonoMovil(clienteDTO.getMovil());
			if(!clienteDTO.getEmail().equals(""))
				PersonValidator.validateEmail(clienteDTO.getEmail());
				
	}

}
