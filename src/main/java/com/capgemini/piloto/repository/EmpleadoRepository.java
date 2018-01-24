package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	List<Empleado> findByMcaModificado(boolean modificado);
	
	Empleado findByDni(String dni);
	
}