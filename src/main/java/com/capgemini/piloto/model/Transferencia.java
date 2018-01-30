package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.dto.GenerarTransferenciaDTO;
import com.capgemini.piloto.model.types.TipoCanal;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transferencia")
public class Transferencia implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858106251027444163L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;


	@Column(name = "Numero_cuenta_destino")
	private String idDestino;

	@ManyToOne
	@JoinColumn(name = "Numero_cuenta_origen")
	private Cuenta cuenta;


	@Column(name = "Fec_transferencia")
	private Date fechaTransferencia;


	@Column(name = "Fec_consolidacion")
	private Date fechaConsolidacion;


	@Enumerated(EnumType.STRING)
	@Column(name = "Canal")
	private TipoCanal canal;

	@Column(name = "Importe")
	private double importe;

	@ManyToOne
	@JoinColumn(name = "dni_empleado")
	private Empleado empleado;

	// Campos de Auditoria

	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActua;

	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;


	@Column(name = "Mca_habilitado", nullable = false)
	private Boolean mCAHabilitado;
	
	@Column(name="Usuario", nullable = false)
	private String usuario;

	public Transferencia() {
	}

	public Transferencia(Transferencia t) {
		idDestino = t.getIdDestino();
		cuenta = t.getCuenta();
		fechaTransferencia = t.getFechaTransferencia();
		fechaConsolidacion = t.getFechaConsolidacion();
		canal = t.getCanal();
		importe = t.getImporte();
		usuario = t.getUsuario();
	}

	public Transferencia(String id_destino, String id_origen, Date fecha_transferencia, Date fecha_consolidacion,
			TipoCanal canal, double importe, Cuenta cuenta, Date fecha_Actua, Date fecha_Creacion, Boolean mCA_Habilitado,
			Empleado empleado) {
		super();
		this.idDestino = id_destino;
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

	public Transferencia(Transferencia t, Cuenta cOrigen, Cuenta cDestino, double importe) {
		super();
		this.idDestino = cDestino.getNumeroCuenta();
		this.fechaTransferencia = t.getFechaTransferencia();
		this.fechaConsolidacion = t.getFechaConsolidacion();
		this.canal = t.getCanal();
		this.importe = importe;
		this.cuenta = cOrigen;
		this.empleado = t.getEmpleado();
		this.fechaActua = t.getFechaActua();
		this.fechaCreacion = t.getFechaCreacion();
		this.mCAHabilitado = true;
		this.usuario = t.getUsuario();
	}

	public Transferencia(GenerarTransferenciaDTO t) {
		super();
		this.idDestino = t.getIdDestino();
		this.fechaTransferencia = new Date();
		this.fechaConsolidacion = new Date();
		this.canal = TipoCanal.ONLINE;
		this.importe = t.getImporte();
		this.cuenta = t.getCuenta();
		this.empleado = null;
		this.fechaActua = new Date();
		this.fechaCreacion = new Date();
		this.mCAHabilitado = true;
		this.usuario = "probador";
	}

	public String getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
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

	public TipoCanal getCanal() {
		return canal;
	}

	public void setCanal(TipoCanal canal) {
		this.canal = canal;
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	@JsonIgnore
	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	@JsonIgnore
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
		return mCAHabilitado;
	}

	public void setMcaHabilitado(Boolean mcaHabilitado) {
		this.mCAHabilitado = mcaHabilitado;
	}

	public Long getId() {
		return id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", id_destino=" + idDestino
				+ ", fecha_transferencia=" + fechaTransferencia + ", fecha_consolidacion=" + fechaConsolidacion
				+ ", canal=" + canal + ", importe=" + importe + ", cuenta=" + cuenta + ", empleado =" + empleado
				+ ", fecha_Actua=" + fechaActua + ", fecha_Creacion=" + fechaCreacion + ", MCA_Habilitado="
				+ mCAHabilitado + "]";
	}

}
