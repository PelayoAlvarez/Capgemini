package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

	@Query("SELECT c FROM Cuenta c WHERE c.mCAHabilitado=true")
	List<Cuenta> findMCA();
}
