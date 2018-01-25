package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Sucursal;

public interface SucursalRepository extends JpaRepository<Sucursal, Long>{
	
	List<Sucursal> findByMcaModificado(Boolean modificado);
	
	Sucursal findById(Long id);

}
