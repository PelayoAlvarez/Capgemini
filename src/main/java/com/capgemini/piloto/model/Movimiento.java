package com.capgemini.piloto.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.types.TipoMovimiento;

@Entity
@Table(name="MOVIMIENTO")
public class Movimiento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double importe;

	@Enumerated(EnumType.STRING)
	private TipoMovimiento tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_hora;

	private String descripcion;

	@OneToMany(mappedBy="cuenta")
	private Cuenta cuentaAsociada;

	// Campos de Auditoria
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Actua;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Creacion;

	// private Empleado empleado;

	private Boolean MCA_Habilitado;
	
	
	Movimiento() {}

	public Movimiento(Double importe, TipoMovimiento tipo, Date fecha, String descripcion, 
			Date fecha_Actua, Date fecha_Creacion, Boolean habilitado) {
		super();
		this.importe = importe;
		this.tipo = tipo;
		this.fecha_hora = fecha;
		this.descripcion = descripcion;
		// this.cuentaAsociada = cuentaAsociada;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		//this.Empleado = empleado;
		this.MCA_Habilitado = habilitado;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fecha_hora;
	}

	public void setFecha(Date fecha) {
		this.fecha_hora = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
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
