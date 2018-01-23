package com.capgemini.piloto.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name="transferencia")
public class Transferencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858106251027444163L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String id_destino;
	private String id_origen;
	private Date fecha_transferencia;
	private Date fecha_consolidacion;
	private String canal;
	private double importe;
	
	@ManyToOne
	private Cuenta cuenta;
	
	// Campos de Auditoria


		@Temporal(TemporalType.TIMESTAMP)
		private Date fecha_Actua;

		@Temporal(TemporalType.TIMESTAMP)
		private Date fecha_Creacion;


		private Boolean MCA_Habilitado;
		
		
		public Transferencia() {}
		
		
	
	public Transferencia(String id_destino, String id_origen, Date fecha_transferencia, Date fecha_consolidacion,
				String canal, double importe, Cuenta cuenta, Date fecha_Actua, Date fecha_Creacion,
				Boolean mCA_Habilitado) {
			super();
			this.id_destino = id_destino;
			this.id_origen = id_origen;
			this.fecha_transferencia = fecha_transferencia;
			this.fecha_consolidacion = fecha_consolidacion;
			this.canal = canal;
			this.importe = importe;
			this.cuenta = cuenta;
			this.fecha_Actua = fecha_Actua;
			this.fecha_Creacion = fecha_Creacion;
			setMCA_Habilitado(mCA_Habilitado);
		}



	public String getId_destino() {
		return id_destino;
	}
	public void setId_destino(String id_destino) {
		this.id_destino = id_destino;
	}
	public String getId_origen() {
		return id_origen;
	}
	public void setId_origen(String id_origen) {
		this.id_origen = id_origen;
	}
	public Date getFecha_transferencia() {
		return fecha_transferencia;
	}
	public void setFecha_transferencia(Date fecha_transferencia) {
		this.fecha_transferencia = fecha_transferencia;
	}
	public Date getFecha_consolidacion() {
		return fecha_consolidacion;
	}
	public void setFecha_consolidacion(Date fecha_consolidacion) {
		this.fecha_consolidacion = fecha_consolidacion;
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
	public Long getId() {
		return id;
	}
	
	public Date getFecha_Actua() {
		return fecha_Actua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fecha_Actua = fecha_Actua;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
	}

	public Boolean getMCA_Habilitado() {
		return MCA_Habilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCA_Habilitado = mCA_Habilitado;
	}



	@Override
	public String toString() {
		return "Transferencia [id=" + id + ", id_destino=" + id_destino + ", id_origen=" + id_origen
				+ ", fecha_transferencia=" + fecha_transferencia + ", fecha_consolidacion=" + fecha_consolidacion
				+ ", canal=" + canal + ", importe=" + importe + ", cuenta=" + cuenta + ", fecha_Actua=" + fecha_Actua
				+ ", fecha_Creacion=" + fecha_Creacion + ", MCA_Habilitado=" + MCA_Habilitado + "]";
	}
	





	
	
	
}
