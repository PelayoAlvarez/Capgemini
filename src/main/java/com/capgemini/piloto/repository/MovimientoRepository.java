package com.capgemini.piloto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long>{
	
	Movimiento findMovimientoById(Long id);
}
