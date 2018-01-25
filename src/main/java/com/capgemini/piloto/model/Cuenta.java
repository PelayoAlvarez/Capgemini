package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

	@ManyToOne
	private Sucursal sucursal;

	@OneToMany
	private Set<ClienteCuenta> clientecuenta = new HashSet<>();

	@Column(name = "Usuario")
	private String usuario;

	@OneToMany
	private Set<Movimiento> movimientos = new HashSet<>();

	@OneToMany(mappedBy = "cuenta")
	private Set<Transferencia> transferencias = new HashSet<>();

	// Campos de Auditoria

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_actu")
	private Date fecActu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_creacion")
	private Date fecCreacion;

	private Empleado empleado;

	private Boolean mCAHabilitado;

	Cuenta() {
		fecCreacion = new Date();
	}

	public Cuenta(String numeroCuenta, Set<Movimiento> movimientos, Set<Transferencia> transferencias,
			Set<ClienteCuenta> clientecuenta, Date fecActu, Date fecCreacion, Boolean mCAHabilitado, String usuario) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.transferencias = transferencias;
		this.clientecuenta = clientecuenta;
		this.fecActu = fecActu;
		this.fecCreacion = fecCreacion;
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

	public Set<Movimiento> getMovimientos() {
		return movimientos;
	}

	protected void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

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

	public void setClientes(Set<ClienteCuenta> clientecuenta) {
		this.clientecuenta = clientecuenta;
	}

	// Getters y Setters de Auditoria

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecActu() {
		return fecActu;
	}

	public void setFecActu(Date fecActu) {
		this.fecActu = fecActu;
	}

	public Boolean getMCAHabilitado() {
		return mCAHabilitado;
	}

	public void setMCAHabilitado(Boolean MCAHabilitado) {
		this.mCAHabilitado = MCAHabilitado;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}
}
