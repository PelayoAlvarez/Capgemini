package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.dto.CuentaDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CUENTA")
public class Cuenta implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283533209815501984L;

	@Id
	@Column(name = "Numero_cuenta")
	private String numeroCuenta;

	@OneToMany(mappedBy = "cuenta")
	@JsonIgnore
	private Set<ClienteCuenta> clientecuenta = new HashSet<>();

	@Column(name = "Usuario")
	private String usuario;
	
	@Column(name = "Importe")
	private Double importe;

	@OneToMany(mappedBy="cuentaAsociada")
	@JsonIgnore
	private Set<Movimiento> movimientos = new HashSet<>();

	@OneToMany(mappedBy = "cuenta")
	@JsonIgnore
	private Set<Transferencia> transferencias = new HashSet<>();

	// Campos de Auditoria

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_actu", nullable = false)
	private Date fecActu;

	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;

	@Column(name = "Mca_habilitado", nullable = false)
	private Boolean mCAHabilitado;

	Cuenta() {
		fecCreacion = new Date();
		fecActu = new Date();
	}
	
	public Cuenta(CuentaDTO cuentadto) {
		this.numeroCuenta=cuentadto.getNumeroCuenta();
		this.usuario=cuentadto.getUsuario();
		this.importe = cuentadto.getImporte();
		this.fecActu=new Date();
		this.fecCreacion=new Date();
		this.mCAHabilitado=true;
	}

	public Cuenta(String numeroCuenta, Set<Movimiento> movimientos, Set<Transferencia> transferencias,
			Set<ClienteCuenta> clientecuenta, Boolean mCAHabilitado, String usuario) {
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
		Cuenta other = (Cuenta) obj;
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

	public Set<Movimiento> _getMovimientos() {
		return new HashSet<>(movimientos);
	}
	
	@JsonIgnore
	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	protected void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	@JsonIgnore
	public Set<Transferencia> getTransferencias() {
		return transferencias;
	}

	public Set<Transferencia> _getTransferencias() {
		return new HashSet<>(transferencias);
	}

	public void setTransferencias(Set<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Set<ClienteCuenta> getClienteCuenta() {
		return clientecuenta;
	}

	public Set<ClienteCuenta> _getClienteCuenta() {
		return new HashSet<>(clientecuenta);
	}

	public void setClienteCuenta(Set<ClienteCuenta> clientecuenta) {
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
	
	
	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}


	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}
	
	

}
