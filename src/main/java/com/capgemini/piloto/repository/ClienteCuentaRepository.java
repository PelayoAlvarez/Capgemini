package com.capgemini.piloto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.types.ClienteCuentaKey;


public interface ClienteCuentaRepository  extends JpaRepository<ClienteCuenta,ClienteCuentaKey>{

}