package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;

public class ClienteBDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String dni;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String apellidos;

	@NotBlank
	private String direccion;
	
	private String movil;
	
	private String fijo;
	
	private String email;

	public ClienteBDTO(String dni, String nombre, String apellidos, String direccion, String movil, String fijo,
			String email) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.email = email;
	}
	
	public ClienteBDTO(Cliente c)
	{
		this.dni=c.getDni();
		this.nombre=c.getNombre();
		this.apellidos=c.getApellidos();
		this.direccion=c.getDireccion();
		this.movil=c.getMovil();
		this.fijo=c.getFijo();
		this.email=c.getEmail();
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
	
	
	
}