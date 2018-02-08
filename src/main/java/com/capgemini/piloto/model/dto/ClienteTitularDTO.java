package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;

public class ClienteTitularDTO implements Serializable {

	private static final long serialVersionUID = 969402650539090726L;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellidos;
	
	@NotBlank
	private String dni;

	public ClienteTitularDTO() {
	}

	public ClienteTitularDTO(String nombre, String apellidos) {
		this.nombre = nombre;
		this.apellidos = apellidos;
	}

	public ClienteTitularDTO(Cliente c) {
		this.nombre = c.getNombre();
		this.apellidos = c.getApellidos();
		this.dni = c.getDni();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	
	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClienteTitularDTO other = (ClienteTitularDTO) obj;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteTitularDTO [nombre=" + nombre + ", apellidos=" + apellidos + "]";
	}
}
