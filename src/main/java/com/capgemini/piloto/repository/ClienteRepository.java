package com.capgemini.piloto.repository;

import java.util.List;

import com.capgemini.piloto.model.Cliente;

public interface ClienteRepository {

	List<Cliente> findAll();

}
