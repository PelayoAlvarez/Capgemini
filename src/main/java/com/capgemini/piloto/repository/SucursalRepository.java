package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
	
	List<Sucursal> findByMcaModificado(char modificado);
	
	Sucursal findById(Long id);

}
