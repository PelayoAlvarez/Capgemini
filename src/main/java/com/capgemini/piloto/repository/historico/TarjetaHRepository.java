package com.capgemini.piloto.repository.historico;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capgemini.piloto.model.Tarjeta;
import com.capgemini.piloto.model.historico.TarjetaH;

public interface TarjetaHRepository extends JpaRepository<TarjetaH, String> {
	
	Tarjeta findByNumeroTarjeta(String numeroTarjeta);

}
