package com.capgemini.piloto.model.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cuenta;

public class GenerarTransferenciaCuentaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283533209815501984L;

	@NotBlank
	private String numeroCuenta;

	private Double importe;

	public GenerarTransferenciaCuentaDTO(Cuenta cuenta) {
		this.numeroCuenta = cuenta.getNumeroCuenta();
		this.importe = cuenta.getImporte();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
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
		GenerarTransferenciaCuentaDTO other = (GenerarTransferenciaCuentaDTO) obj;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "GenerarTransferenciaCuentaDTO [numeroCuenta=" + numeroCuenta + ", importe=" + importe + "]";
	}

}
