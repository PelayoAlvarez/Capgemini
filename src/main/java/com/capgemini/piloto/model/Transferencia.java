package com.capgemini.piloto.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="transferencia")
public class Transferencia {

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	private String id_destino;
	private String id_origen;
	private Date fecha_transferencia;
	private Date fecha_consolidacion;
	private String canal;
	private double importe;
	
	@ManyToOne
	private Cuenta cuenta;
	
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
	public int getId() {
		return id;
	}
	
	@Override
	public String toString() {
		return "TransferenciaModel [id=" + id + ", id_destino=" + id_destino + ", id_origen=" + id_origen
				+ ", fecha_transferencia=" + fecha_transferencia + ", fecha_consolidacion=" + fecha_consolidacion
				+ ", canal=" + canal + ", importe=" + importe + "]";
	}
	
	
	
}
