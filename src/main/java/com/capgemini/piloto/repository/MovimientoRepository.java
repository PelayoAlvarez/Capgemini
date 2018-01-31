package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	Movimiento findMovimientoById(Long id);

	List<Movimiento> findByCuentaAsociada(Cuenta cuenta);
}
