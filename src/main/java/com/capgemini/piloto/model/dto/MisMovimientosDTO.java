package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.text.SimpleDateFormat;

import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.types.TipoMovimiento;

public class MisMovimientosDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8990419489418238745L;
	
	private Long id;

	private String fecha;
	
	private double importe;

	private TipoMovimiento tipo;

	private String descripcion;

	public MisMovimientosDTO(Movimiento m) {
		this.id = m.getId();
		this.importe = m.getImporte();
		this.tipo = m.getTipo();
		this.descripcion = m.getDescripcion();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy -- hh:mm:ss");
		this.fecha = formatter.format(m.getFechahora());
	}

	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
