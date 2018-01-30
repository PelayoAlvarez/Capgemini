package com.capgemini.piloto.model.dto;

import com.capgemini.piloto.model.Tarjeta;

public class TarjetaDTO {

	private String numeroTarjeta;
	private String numeroCuenta;
	private Double importe;
	
	public TarjetaDTO(Tarjeta tarjeta) {
		this.numeroTarjeta = tarjeta.getNumeroTarjeta();
		this.numeroCuenta = tarjeta.getClienteCuenta().getCuenta().getNumeroCuenta();
	}
}
