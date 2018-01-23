package com.capgemini.piloto.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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

}
