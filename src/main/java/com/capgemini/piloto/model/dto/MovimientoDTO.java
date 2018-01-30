package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.Date;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.types.TipoMovimiento;

public class MovimientoDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private Double importe;

	private TipoMovimiento tipo;

	private Date fechahora;

	private String descripcion;

	private Cuenta cuentaAsociada;
	
	private String usuario;
	
	public MovimientoDTO() {
		
	}

	public MovimientoDTO(Movimiento m) {
		super();
		this.id = m.getId();
		this.importe = m.getImporte();
		this.tipo = m.getTipo();
		this.fechahora = m.getFechahora();
		this.descripcion = m.getDescripcion();
		this.cuentaAsociada = m.getCuentaAsociada();
		this.usuario = m.getUsuario();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}

	public void setCuentaAsociada(Cuenta cuentaAsociada) {
		this.cuentaAsociada = cuentaAsociada;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
}
