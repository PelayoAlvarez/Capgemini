package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import com.capgemini.piloto.model.Tarjeta;

public class CrearTarjetaDTO implements Serializable{

	private static final long serialVersionUID = -4883586195871239103L;
	
	private String dni;
	private String numeroCuenta;
	
	public CrearTarjetaDTO() {
		
	}
	
	public CrearTarjetaDTO(Tarjeta tarjeta) {
		this.dni = tarjeta.getClienteCuenta().getCliente().getDni();
		this.numeroCuenta = tarjeta.getClienteCuenta().getCuenta().getNumeroCuenta();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	

	
	
	
}
