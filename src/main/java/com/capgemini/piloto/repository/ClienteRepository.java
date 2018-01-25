package com.capgemini.piloto.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, String>{

	@Query("SELECT c FROM Cliente c WHERE c.dni = :dni")
	Cliente findByDni(String dni);
	
	@Query("SELECT c FROM Cliente c WHERE c.MCA_Habilitado=1")
	List<Cliente> findMCA();

}
