package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Tarjeta;

public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {

	@Query("SELECT t FROM Tarjeta t WHERE t.mcaHabilitado=true")
	List<Tarjeta> findByMcaHabilitado();
	
	@Query("select t from Tarjeta t where t.clienteCuenta.cliente.dni = ?1")
	List<Tarjeta> getAllTarjetasByDni(String dni);

}
