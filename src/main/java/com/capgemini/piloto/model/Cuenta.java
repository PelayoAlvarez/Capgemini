package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="CUENTA")
public class Cuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283533209815501984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String numeroCuenta;

	@OneToMany
	private Set<Cliente> clientes = new HashSet<Cliente>();
	
	@OneToMany
	private Set<Movimiento> movimientos = new HashSet<Movimiento>();
	
	@OneToMany(mappedBy="cuenta")
	private Set<Transferencia> transferencias = new HashSet<Transferencia>();

	// Campos de Auditoria

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Actua;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Creacion;

	private Empleado empleado;

	private Boolean MCA_Habilitado;
	
	
	public Cuenta() {}
	
	public Cuenta(String numeroCuenta, Set<Movimiento> movimientos, Date fecha_Actua, Date fecha_Creacion,
			Boolean mCA_Habilitado) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		this.MCA_Habilitado = mCA_Habilitado;
	}

	public Long getId() {
		return id;
	}
	
	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}
	
	public Set<Movimiento> getMovimientos() {
		return new HashSet<Movimiento>(movimientos);
	}

	protected void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}
	
	//Getters y Setters de Auditoria

	public Date getFecha_Actua() {
		return fecha_Actua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fecha_Actua = fecha_Actua;
	}

	public Boolean getMCA_Habilitado() {
		return MCA_Habilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCA_Habilitado = mCA_Habilitado;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}
}
