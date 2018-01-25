package com.capgemini.piloto.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	
	Cliente findByDni(String dni);
	
	@Query("SELECT c FROM Cliente c WHERE c.mCAHabilitado=true")
	List<Cliente> findMCA();

}
