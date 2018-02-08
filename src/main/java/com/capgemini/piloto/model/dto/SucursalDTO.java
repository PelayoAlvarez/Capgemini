package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import com.capgemini.piloto.model.Sucursal;

public class SucursalDTO implements Serializable {

	private static final long serialVersionUID = -8890439489922274501L;

	private Long id;
	private String nombre;
	private String direccion;
	private String usuario;

	public SucursalDTO() {

	}

	public SucursalDTO(Sucursal sucursal) {
		super();
		this.id = sucursal.getId();
		this.nombre = sucursal.getNombre();
		this.direccion = sucursal.getDireccion();
		this.usuario = sucursal.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

}
