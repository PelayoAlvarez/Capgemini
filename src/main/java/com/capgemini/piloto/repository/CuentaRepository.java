package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta, Long> {

	@Query("SELECT c FROM Cuenta c WHERE c.MCA_Habilitado=0")
	List<Cuenta> findMCA();
}
