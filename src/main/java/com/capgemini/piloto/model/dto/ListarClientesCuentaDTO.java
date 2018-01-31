package com.capgemini.piloto.model.dto;

import com.capgemini.piloto.model.Cuenta;

public class ListarClientesCuentaDTO {

	private double importe;
	private String numeroCuenta;

	public ListarClientesCuentaDTO(Cuenta cuenta) {
		this.numeroCuenta = cuenta.getNumeroCuenta();
		this.importe = cuenta.getImporte();
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

}