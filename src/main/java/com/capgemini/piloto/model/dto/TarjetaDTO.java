package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import com.capgemini.piloto.model.Tarjeta;

public class TarjetaDTO implements Serializable{

	private static final long serialVersionUID = -4883586195871239103L;
	private String numeroTarjeta;
	private String numeroCuenta;
	private Double importe;
	
	public TarjetaDTO(Tarjeta tarjeta) {
		this.numeroTarjeta = tarjeta.getNumeroTarjeta();
		this.numeroCuenta = tarjeta.getClienteCuenta().getCuenta().getNumeroCuenta();
		this.importe = tarjeta.getClienteCuenta().getCuenta().getImporte();
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	
}
