package com.capgemini.piloto.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.PasswordFormatException;
import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.dto.ClienteDTO;
import com.capgemini.piloto.repository.LoginRepository;
import com.capgemini.piloto.util.validator.PersonValidator;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private LoginRepository lRepository;
	
	@PutMapping("/{dni}{password}")
	public ResponseEntity<ClienteDTO> login(@PathVariable(value="dni") String dni, 
			@PathVariable(value="password") String password){
		if(dni != null && password != null) {
			try {
				PersonValidator.validateDni(dni);
				PersonValidator.validatePassword(password);
				
				Cliente cliente = lRepository.findUserWithPassword(dni, password);
				if(cliente!=null) {
					return new ResponseEntity<>(new ClienteDTO(cliente), new HttpHeaders(), HttpStatus.OK);
				}
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
			}
			catch(DniFormatException e) {
				logger.error("CheckDNI: "+e.getMessage());
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			catch(PasswordFormatException e) {
				logger.error("CheckPassword: "+e.getMessage());
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
