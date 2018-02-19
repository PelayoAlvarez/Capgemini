package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Transferencia;

public interface TransferenciaRepository extends JpaRepository<Transferencia, Long> {

	@Query("SELECT t FROM Transferencia t WHERE t.mCAHabilitado=true")
	List<Transferencia> findMCA();


	List<Transferencia> findByCuenta(Cuenta cuenta);

}