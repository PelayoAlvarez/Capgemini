package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.types.TipoRole;

public class LoginDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotBlank
	private String dni;
	
	@NotBlank
	private String password;
	
	@NotBlank
	private String token;
	
	private TipoRole role;
	
	LoginDTO() {}
	
	public LoginDTO(String dni, String password, String token) {
		this.dni = dni;
		this.password = password;
		this.token = token;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public TipoRole getRole() {
		return role;
	}

	public void setRole(TipoRole role) {
		this.role = role;
	}
}
