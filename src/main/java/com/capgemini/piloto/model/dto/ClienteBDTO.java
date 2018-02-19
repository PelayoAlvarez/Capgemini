package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;

public class ClienteBDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 3440129384482107650L;

	@NotBlank
	private String dni;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellidos;

	@NotBlank
	private String direccion;
	
	@NotBlank
	private String password;

	private String movil;

	private String fijo;

	private String email;

	public ClienteBDTO(Cliente c) {
		this.dni = c.getDni();
		this.nombre = c.getNombre();
		this.apellidos = c.getApellidos();
		this.direccion = c.getDireccion();
		this.movil = c.getMovil();
		this.fijo = c.getFijo();
		this.email = c.getEmail();
		this.password = c.getPassword();
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

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDni() {
		return dni;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}