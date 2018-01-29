package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

	@Query("SELECT e FROM Empleado e WHERE e.mcaHabilitado=true")
	List<Empleado> findByMcaHabilitado();
	
	Empleado findByDni(String dni);
	
}
