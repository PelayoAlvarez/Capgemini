package com.capgemini.piloto.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858106251027444163L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "Numero_cuenta_destino")
	private String idDestino;

	@NotBlank
	@Column(name = "Numero_cuenta_origen")
	private String idOrigen;

	@NotBlank
	@Column(name = "Fec_transferencia")
	private Date fechaTransferencia;

	@NotBlank
	@Column(name = "Fec_consolidacion")
	private Date fechaConsolidacion;

	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name = "Canal")
	private String canal;

	@NotBlank
	@Column(name = "Importe")
	private double importe;

	@ManyToOne
	private Cuenta cuenta;

	@ManyToOne
	private Empleado empleado;

	// Campos de Auditoria

	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActua;

	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@NotBlank
	@Column(name = "Mca_habilitado")
	private Boolean mcaHabilitado;

	public Transferencia() {
	}

	public Transferencia(Transferencia t) {
		idDestino = t.getIdDestino();
		idOrigen = t.getIdOrigen();
		fechaTransferencia = t.getFechaTransferencia();
		fechaConsolidacion = t.getFechaConsolidacion();
		canal = t.getCanal();
		importe = t.getImporte();
	}

	public Transferencia(String id_destino, String id_origen, Date fecha_transferencia, Date fecha_consolidacion,
			String canal, double importe, Cuenta cuenta, Date fecha_Actua, Date fecha_Creacion, Boolean mCA_Habilitado,
			Empleado empleado) {
		super();
		this.idDestino = id_destino;
		this.idOrigen = id_origen;
		this.fechaTransferencia = fecha_transferencia;
		this.fechaConsolidacion = fecha_consolidacion;
		this.canal = canal;
		this.importe = importe;
		this.cuenta = cuenta;
		this.empleado = empleado;
		this.fechaActua = fecha_Actua;
		this.fechaCreacion = fecha_Creacion;
		setMcaHabilitado(mCA_Habilitado);
	}

	public String getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}

	public String getIdOrigen() {
		return idOrigen;
	}

	public void setIdOrigen(String idOrigen) {
		this.idOrigen = idOrigen;
	}

	public Date getFechaTransferencia() {
		return fechaTransferencia;
	}

	public void setFechaTransferencia(Date fechaTransferencia) {
		this.fechaTransferencia = fechaTransferencia;
	}

	public Date getFechaConsolidacion() {
		return fechaConsolidacion;
	}

	public void setFechaConsolidacion(Date fechaConsolidacion) {
		this.fechaConsolidacion = fechaConsolidacion;
	}

	public String getCanal() {
		return canal;
	}

	public void setCanal(String canal) {
		this.canal = canal;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Date getFechaActua() {
		return fechaActua;
	}

	public void setFechaActua(Date fechaActua) {
		this.fechaActua = fechaActua;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(Boolean mcaHabilitado) {
		this.mcaHabilitado = mcaHabilitado;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", id_destino=" + idDestino + ", id_origen=" + idOrigen
				+ ", fecha_transferencia=" + fechaTransferencia + ", fecha_consolidacion=" + fechaConsolidacion
				+ ", canal=" + canal + ", importe=" + importe + ", cuenta=" + cuenta + ", empleado =" + empleado
				+ ", fecha_Actua=" + fechaActua + ", fecha_Creacion=" + fechaCreacion + ", MCA_Habilitado="
				+ mcaHabilitado + "]";
	}

}
