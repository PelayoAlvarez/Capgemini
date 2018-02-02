package com.capgemini.piloto.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.types.ClienteCuentaKey;

public interface ClienteCuentaRepository extends JpaRepository<ClienteCuenta, ClienteCuentaKey> {

	@Query("SELECT c FROM ClienteCuenta c WHERE c.cliente.dni=?1 AND c.mcaHabilitado=true")
	List<ClienteCuenta> findByDni(String dni);

	@Query("SELECT c FROM ClienteCuenta c WHERE c.cuenta.numeroCuenta=?1 AND c.mcaHabilitado=true")
	List<ClienteCuenta> findByNumeroCuenta(String numeroCuenta);

}