package com.capgemini.piloto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.Empleado;

public interface LoginRepository extends JpaRepository<Cliente, String> {
	
	@Query("SELECT c FROM Cliente c WHERE c.mCAHabilitado=true AND c.dni=?1 AND c.password=?2")
	Cliente findUserWithPassword(String dni, String password);
	
	@Query("SELECT e FROM Empleado e WHERE e.mcaHabilitado=true AND e.dni=?1 AND e.password=?2")
	Empleado findEmpleadoWithPassword(String dni, String password);
}
