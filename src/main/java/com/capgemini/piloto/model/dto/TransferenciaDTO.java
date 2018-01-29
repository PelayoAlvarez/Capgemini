package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.Date;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.types.TipoCanal;

public class TransferenciaDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Long id;
	private String idDestino;
	private Date fechaTransferencia;
	private Date fechaConsolidacion;
	private TipoCanal canal;
	private double importe;
	private Cuenta cuenta;
	private Empleado empleado;
	
	public TransferenciaDTO(){
		
	}
	
	public TransferenciaDTO(Transferencia t) {
		idDestino = t.getIdDestino();
		cuenta = t.getCuenta();
		fechaTransferencia = t.getFechaTransferencia();
		fechaConsolidacion = t.getFechaConsolidacion();
		canal = t.getCanal();
		importe = t.getImporte();
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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	

}
