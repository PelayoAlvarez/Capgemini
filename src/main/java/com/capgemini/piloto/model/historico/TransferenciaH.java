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
@Table(name="transferenciaH")
public class TransferenciaH implements Serializable{

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
	private TipoCanal canal;

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
		
		
		public TransferenciaH() {}
		
		public TransferenciaH(Transferencia t) {
			idDestino = t.getIdDestino();
			idOrigen = t.getIdOrigen();
			fechaTransferencia = t.getFechaTransferencia();
			fechaConsolidacion = t.getFechaConsolidacion();
			canal = t.getCanal();
			importe = t.getImporte();
		}
		
		
	
	public TransferenciaH(String id_destino, String id_origen, Date fecha_transferencia, Date fecha_consolidacion,
				TipoCanal canal, double importe, Cuenta cuenta, Date fecha_Actua, Date fecha_Creacion,
				Boolean mCA_Habilitado, Empleado empleado) {
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
			this.fechaConsolidacion = fecha_Creacion;
			setMCA_Habilitado(mCA_Habilitado);
		}



	public String getId_destino() {
		return idDestino;
	}
	public void setId_destino(String id_destino) {
		this.idDestino = id_destino;
	}
	public String getId_origen() {
		return idOrigen;
	}
	public void setId_origen(String id_origen) {
		this.idOrigen = id_origen;
	}
	public Date getFecha_transferencia() {
		return fechaTransferencia;
	}
	public void setFecha_transferencia(Date fecha_transferencia) {
		this.fechaTransferencia = fecha_transferencia;
	}
	public Date getFecha_consolidacion() {
		return fechaConsolidacion;
	}
	public void setFecha_consolidacion(Date fecha_consolidacion) {
		this.fechaConsolidacion = fecha_consolidacion;
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
	
	public Date getFecha_Actua() {
		return fechaActua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fechaActua = fecha_Actua;
	}

	public Date getFecha_Creacion() {
		return fechaConsolidacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fechaConsolidacion = fecha_Creacion;
	}

	public Boolean getMCA_Habilitado() {
		return getMCA_Habilitado();
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		mcaHabilitado = mCA_Habilitado;
	}



	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", id_destino=" + idDestino + ", id_origen=" + idOrigen
				+ ", fecha_transferencia=" + fechaTransferencia + ", fecha_consolidacion=" + fechaConsolidacion
				+ ", canal=" + canal + ", importe=" + importe + ", cuenta=" + cuenta + ", empleado =" + empleado + ", fecha_Actua=" + fechaActua
				+ ", fecha_Creacion=" + fechaConsolidacion + ", MCA_Habilitado=" + mcaHabilitado + "]";
	}
	





	
	
	
}
