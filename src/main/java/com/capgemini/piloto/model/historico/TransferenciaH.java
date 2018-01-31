package com.capgemini.piloto.model.historico;

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

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.types.TipoCanal;

@Entity
@Table(name = "transferencia_H")
public class TransferenciaH implements Serializable {

	private static final long serialVersionUID = -2858106251027444163L;

	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Column(name = "Numero_cuenta_destino")
	private String idDestino;

	@NotBlank
	@Column(name = "Fec_transferencia")
	private Date fechaTransferencia;

	@NotBlank
	@Column(name = "Fec_consolidacion")
	private Date fechaConsolidacion;

	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name = "Canal")
	private TipoCanal canal;

	@NotBlank
	@Column(name = "Importe")
	private double importe;

	@ManyToOne
	@JoinColumn(name = "numero_cuenta")
	private Cuenta cuenta;

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

	@Id
	@Column(name = "Fec_audit")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAudit;

	@NotBlank
	@Column(name = "Mca_habilitado")
	private Boolean mcaHabilitado;

	public TransferenciaH() {
	}

	public TransferenciaH(Transferencia t) {
		idDestino = t.getIdDestino();
		cuenta = t.getCuenta();
		fechaTransferencia = t.getFechaTransferencia();
		fechaConsolidacion = t.getFechaConsolidacion();
		canal = t.getCanal();
		importe = t.getImporte();
	}

	public String getIddestino() {
		return idDestino;
	}

	public void setIddestino(String iddestino) {
		this.idDestino = iddestino;
	}

	public Date getFechatransferencia() {
		return fechaTransferencia;
	}

	public void setFechatransferencia(Date fechatransferencia) {
		this.fechaTransferencia = fechatransferencia;
	}

	public Date getFechaconsolidacion() {
		return fechaConsolidacion;
	}

	public void setFechaconsolidacion(Date fechaconsolidacion) {
		this.fechaConsolidacion = fechaconsolidacion;
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

	public Long getId() {
		return id;
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

	public Date getFecAudit() {
		return fecAudit;
	}

	public void setFecAudit(Date fecAudit) {
		this.fecAudit = fecAudit;
	}

	public Boolean getMCAHabilitado() {
		return getMCAHabilitado();
	}

	public void setMCAHabilitado(Boolean mCAHabilitado) {
		mcaHabilitado = mCAHabilitado;
	}

	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", id_destino=" + idDestino + ", fecha_transferencia=" + fechaTransferencia
				+ ", fecha_consolidacion=" + fechaConsolidacion + ", canal=" + canal + ", importe=" + importe
				+ ", cuenta=" + cuenta + ", empleado =" + empleado + ", fecha_Actua=" + fechaActua + ", fecha_Creacion="
				+ fechaConsolidacion + ", MCA_Habilitado=" + mcaHabilitado + "]";
	}

}
