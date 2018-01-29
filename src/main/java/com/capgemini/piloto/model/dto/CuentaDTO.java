package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Dictionary;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;


public class CuentaDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283533209815501984L;

	@NotBlank
	private String numeroCuenta;

	private Set<Dictionary<Long,Long>> clientecuenta = new HashSet<>();

	private String usuario;

	private Set<Long> movimientos = new HashSet<>();

	private Set<Long> transferencias = new HashSet<>();

	// Campos de Auditoria

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}

	private Date fecActu;

	private Date fecCreacion;

	private Boolean mCAHabilitado;

	CuentaDTO() {
		fecCreacion = new Date();
		fecActu = new Date();
	}

	public CuentaDTO(String numeroCuenta, Set<Long> movimientos, Set<Long> transferencias,
			Set<Dictionary<Long,Long>> clientecuenta, Boolean mCAHabilitado, String usuario) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.transferencias = transferencias;
		this.clientecuenta = clientecuenta;
		this.fecActu = new Date();
		this.fecCreacion = new Date();
		this.mCAHabilitado = mCAHabilitado;
		this.usuario = usuario;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CuentaDTO other = (CuentaDTO) obj;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<Long> _getMovimientos() {
		return new HashSet<>(movimientos);
	}

	public Set<Long> getMovimientos() {
		return movimientos;
	}

	protected void setMovimientos(Set<Long> movimientos) {
		this.movimientos = movimientos;
	}
	
	public Set<Long> getTransferencias() {
		return transferencias;
	}

	public Set<Long> _getTransferencias() {
		return new HashSet<>(transferencias);
	}

	public void setTransferencias(Set<Long> transferencias) {
		this.transferencias = transferencias;
	}

	public Set<Dictionary<Long,Long>> getClienteCuenta() {
		return clientecuenta;
	}

	public Set<Dictionary<Long,Long>> _getClienteCuenta() {
		return new HashSet<>(clientecuenta);
	}

	public void setClienteCuenta(Set<Dictionary<Long,Long>> clientecuenta) {
		this.clientecuenta = clientecuenta;
	}

	// Getters y Setters de Auditoria

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getmCAHabilitado() {
		return mCAHabilitado;
	}

	public void setmCAHabilitado(Boolean mCAHabilitado) {
		this.mCAHabilitado = mCAHabilitado;
	}

	public Date getFecActu() {
		return fecActu;
	}

	public void setFecActu(Date fecActu) {
		this.fecActu = fecActu;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

}
