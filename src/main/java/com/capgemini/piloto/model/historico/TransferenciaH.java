package com.capgemini.piloto.model.historico;

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

import com.capgemini.piloto.model.Transferencia;


@Entity
@Table(name="transferenciaH")
public class TransferenciaH implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2858106251027444163L;

	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(name="Numero_cuenta_destino")
	private String id_destino;
	
	@NotBlank
	@Column(name="Numero_cuenta_origen")
	private String id_origen;
	
	
	@NotBlank
	@Column(name="Fec_transferencia")
	private Date fecha_transferencia;
	
	
	@NotBlank
	@Column(name="Fec_consolidacion")
	private Date fecha_consolidacion;
	
	
	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name="Canal")
	private String canal;
	
	
	@NotBlank
	@Column(name="Importe")
	private double importe;
	
	@ManyToOne
	private CuentaH cuenta;
	
	@ManyToOne
	private EmpleadoH empleado;
	
	// Campos de Auditoria


		@Column(name="Fec_actu", nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		private Date fecha_Actua;

		@Column(name="Fec_creacion", nullable = false)
		@Temporal(TemporalType.TIMESTAMP)
		private Date fecha_Creacion;


		@NotBlank
		@Column(name="Mca_habilitado")
		private Boolean MCA_Habilitado;
		
		
		public TransferenciaH() {}
		
		public TransferenciaH(Transferencia t) {
			id_destino = t.getId_destino();
			id_origen = t.getId_origen();
			fecha_transferencia = t.getFecha_transferencia();
			fecha_consolidacion = t.getFecha_consolidacion();
			canal = t.getCanal();
			importe = t.getImporte();
		}
		
		
	
	public TransferenciaH(String id_destino, String id_origen, Date fecha_transferencia, Date fecha_consolidacion,
				String canal, double importe, CuentaH cuenta, Date fecha_Actua, Date fecha_Creacion,
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
