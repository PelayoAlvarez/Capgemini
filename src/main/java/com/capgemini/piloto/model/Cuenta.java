package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
	@Column(name="Numero_cuenta")
	private String numeroCuenta;
	
	@ManyToOne
	private Sucursal sucursal;
	
	@OneToMany
	private Set<Cliente> clientes = new HashSet<>();

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
	@Column(name="Fec_actu")
	private Date fecActu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="Fec_creacion")
	private Date fecCreacion;

	private Empleado empleado;

	private Boolean mCAHabilitado;

	Cuenta() {
		fecCreacion = new Date();
	}

	public Cuenta(String numeroCuenta, Set<Movimiento> movimientos, Date fecActu, Date fecCreacion,
			Boolean mCAHabilitado) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.fecActu = fecActu;
		this.fecCreacion = fecCreacion;
		this.mCAHabilitado = mCAHabilitado;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<Movimiento> getMovimientos() {
		return new HashSet<>(movimientos);
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

	public void setTransferencias(Set<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Set<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}

	// Getters y Setters de Auditoria

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
