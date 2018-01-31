package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.Cuenta;

public class MisCuentasDTO implements Serializable {

	private static final long serialVersionUID = -2405890651955664095L;

	@NotBlank
	private String numeroCuenta;

	private List<ClienteTitularDTO> titulares;

	public MisCuentasDTO() {
	}

	public MisCuentasDTO(String numeroCuenta, List<ClienteTitularDTO> titulares) {
		this.numeroCuenta = numeroCuenta;
		this.titulares = titulares;
	}

	public MisCuentasDTO(Cuenta cuenta, List<Cliente> clientes) {
		this.numeroCuenta = cuenta.getNumeroCuenta();
		clientes.forEach(cliente -> this.titulares.add(new ClienteTitularDTO(cliente)));
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public List<ClienteTitularDTO> getTitulares() {
		return titulares;
	}

	public void setTitulares(List<ClienteTitularDTO> titulares) {
		this.titulares = titulares;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
		result = prime * result + ((titulares == null) ? 0 : titulares.hashCode());
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
		MisCuentasDTO other = (MisCuentasDTO) obj;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		if (titulares == null) {
			if (other.titulares != null)
				return false;
		} else if (!titulares.equals(other.titulares))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "MisCuentasDTO [numeroCuenta=" + numeroCuenta + ", titulares=" + titulares + "]";
	}
}
