package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

	Movimiento findMovimientoById(Long id);
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuentaAsociada=?1 "
			+ "AND m.mCAHabilitado=true ORDER BY m.fechahora DESC")
	List<Movimiento> findByCuentaAsociada(Cuenta cuenta);
	
	@Query("SELECT m FROM Movimiento m WHERE m.cuentaAsociada.numeroCuenta=?1 "
			+ "AND m.mCAHabilitado=true ORDER BY m.fechahora DESC")
	List<Movimiento> findByNumeroCuenta(String cuenta);
}
