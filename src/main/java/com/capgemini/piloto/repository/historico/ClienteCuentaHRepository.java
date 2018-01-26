package com.capgemini.piloto.repository.historico;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.historico.ClienteCuentaH;


public interface ClienteCuentaHRepository  extends JpaRepository<ClienteCuentaH,Date>{

}