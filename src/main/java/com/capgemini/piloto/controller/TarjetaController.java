package com.capgemini.piloto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Tarjeta;
import com.capgemini.piloto.model.dto.TarjetaDTO;
import com.capgemini.piloto.repository.TarjetaRepository;

@RestController
@RequestMapping("/tarjetas")
public class TarjetaController {
	
	@Autowired
	private TarjetaRepository tarjetaRep;
	
	@GetMapping("/{dni}")
	public List<TarjetaDTO> getTarjetaByDni(@PathVariable String dni){
		List<Tarjeta> tarjetas = tarjetaRep.getAllTarjetasByDni(dni);
		List<TarjetaDTO> tarjetasDto = new ArrayList<>();
		tarjetas.forEach(tarjeta -> tarjetasDto.add(new TarjetaDTO(tarjeta)));
		return tarjetasDto;
	}

}
