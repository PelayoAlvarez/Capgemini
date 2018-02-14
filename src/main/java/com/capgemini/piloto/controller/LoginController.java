package com.capgemini.piloto.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.PasswordFormatException;
import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.dto.ClienteDTO;
import com.capgemini.piloto.model.dto.LoginDTO;
import com.capgemini.piloto.repository.LoginRepository;
import com.capgemini.piloto.util.validator.PersonValidator;

@RestController
@RequestMapping("/login")
@CrossOrigin
public class LoginController {
	
	private static final Logger logger = LoggerFactory.getLogger(ClienteController.class);
	
	@Autowired
	private LoginRepository lRepository;
	
	@PostMapping("/")
	public ResponseEntity<ClienteDTO> login(HttpSession session, @RequestBody LoginDTO user){
		if(user.getDni() != null && user.getPassword() != null && user.getToken() != null) {
			try {
				PersonValidator.validateDni(user.getDni());
				PersonValidator.validatePassword(user.getPassword());
				
				Cliente cliente = lRepository.findUserWithPassword(user.getDni(), user.getPassword());
				if(cliente!=null) {
					logger.info("El cliente con dni: " + cliente.getDni() + " ha iniciado sesión");
					session.getServletContext().setAttribute("USER", user);
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
	
	@PostMapping("/out")
	public void logOut(HttpSession session) {
		LoginDTO user = (LoginDTO)session.getServletContext().getAttribute("USER");
		System.out.println("El usuario con dni: " + user.getDni() + " ha finalizado su sesión");
		session.getServletContext().setAttribute("USER", null);
	}
}
