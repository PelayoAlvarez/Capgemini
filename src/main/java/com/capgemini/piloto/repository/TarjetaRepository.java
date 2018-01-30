package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, Long> {

	@Query("SELECT t FROM Tarjeta t WHERE t.mcaHabilitado=true")
	List<Tarjeta> findByMcaHabilitado();
	
	Tarjeta findById(String numeroTarjeta);
	
}
