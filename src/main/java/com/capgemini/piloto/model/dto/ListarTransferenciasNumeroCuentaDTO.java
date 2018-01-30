package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.Date;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.types.TipoCanal;

public class ListarTransferenciasNumeroCuentaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	private String idDestino;
	private double importe;
	private String cuenta;
	private Date fechaRealizacion;
	
	public ListarTransferenciasNumeroCuentaDTO(){
		
	}
	
	public ListarTransferenciasNumeroCuentaDTO(Transferencia t) {
		idDestino = t.getIdDestino();
		cuenta = t.getCuenta().getNumeroCuenta();
		importe = t.getImporte();
		fechaRealizacion = t.getFechaCreacion();
	}
	
	public String getIdDestino() {
		return idDestino;
	}

	public void setIdDestino(String idDestino) {
		this.idDestino = idDestino;
	}


	public double getImporte() {
		return importe;
	}

	public void setImporte(double importe) {
		this.importe = importe;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}
	
	


	

}
