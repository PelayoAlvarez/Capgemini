package com.capgemini.piloto.service;

import java.util.List;

import com.capgemini.piloto.model.Movimiento;

public interface MovimientoService {
	
	public Movimiento addMovimiento(Movimiento m);
	public void removeMovimiento(Movimiento m);
	public Movimiento updateMovimiento(Movimiento m);
	public Movimiento getMovimientoById(Long id);
	public List<Movimiento> listMovimiento();
}
