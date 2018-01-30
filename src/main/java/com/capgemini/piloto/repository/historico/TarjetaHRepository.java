package com.capgemini.piloto.repository.historico;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.capgemini.piloto.model.Tarjeta;
import com.capgemini.piloto.model.historico.TarjetaH;

public interface TarjetaHRepository extends JpaRepository<TarjetaH, String> {
	
	Tarjeta findByNumeroTarjeta(String numeroTarjeta);

}
