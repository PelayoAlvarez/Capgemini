package com.capgemini.piloto.model.dto;

public class TitularDTO {

	private String dniTitular;
	
	private String nombre;
	
	private String apellidos;

	public String getDniTitular() {
		return dniTitular;
	}

	public void setDniTitular(String dniTitular) {
		this.dniTitular = dniTitular;
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

	public TitularDTO(String dniTitular, String nombre, String apellidos) {
		super();
		this.dniTitular = dniTitular;
		this.nombre = nombre;
		this.apellidos = apellidos;
	}
	
	public TitularDTO(String dniTitular) {
		super();
		this.dniTitular = dniTitular;
	}
	
	
	public TitularDTO() {
		
	}
}
