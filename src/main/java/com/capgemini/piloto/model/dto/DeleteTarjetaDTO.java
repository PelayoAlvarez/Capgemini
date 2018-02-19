package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.List;


public class DeleteTarjetaDTO implements Serializable {

	private static final long serialVersionUID = -4883586195871239103L;
	private List<String> numeroTarjetas;

	public DeleteTarjetaDTO() {		
	}

	public List<String> getNumeroTarjetas() {
		return numeroTarjetas;
	}

	public void setNumeroTarjetas(List<String> numeroTarjetas) {
		this.numeroTarjetas = numeroTarjetas;
	}

}
