package com.capgemini.piloto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Cuenta;

public interface CuentaRepository extends JpaRepository<Cuenta,Long>{

}
