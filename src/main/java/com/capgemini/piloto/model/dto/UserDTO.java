package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import com.capgemini.piloto.model.types.TipoRole;

public class UserDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dni;
	
	private String password;
	
	private TipoRole role;

	public UserDTO(String dni, String password, TipoRole role) {
		super();
		this.dni = dni;
		this.password = password;
		this.role = role;
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

	public TipoRole getRole() {
		return role;
	}
}
