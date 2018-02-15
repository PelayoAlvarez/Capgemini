package com.capgemini.piloto.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Tarjeta;
import com.capgemini.piloto.model.dto.CrearTarjetaDTO;
import com.capgemini.piloto.model.dto.TarjetaDTO;
import com.capgemini.piloto.model.historico.TarjetaH;
import com.capgemini.piloto.model.types.ClienteCuentaKey;
import com.capgemini.piloto.repository.ClienteCuentaRepository;
import com.capgemini.piloto.repository.TarjetaRepository;
import com.capgemini.piloto.repository.historico.TarjetaHRepository;
import com.capgemini.piloto.util.validator.PersonValidator;

@RestController
@RequestMapping("/tarjetas")
@CrossOrigin
public class TarjetaController {
	
	// Logger
	private Logger log = LoggerFactory.getLogger(getClass());

	@Autowired
	private TarjetaRepository tarjetaRep;
	@Autowired
	private TarjetaHRepository tarjetaRepH;
	@Autowired
	private ClienteCuentaRepository clientCuentaRep;

	@GetMapping
	public List<TarjetaDTO> getAllTarjetas() {
		List<Tarjeta> tarjetas = tarjetaRep.findAll();
		List<TarjetaDTO> tarjetasDto = new ArrayList<>();
		tarjetas.forEach(tarjeta -> tarjetasDto.add(new TarjetaDTO(tarjeta)));
		log.info("GET: Se obtienen todas las tarjetas");
		return tarjetasDto;
	}
	
	@GetMapping("/{dni}")
	public List<TarjetaDTO> getTarjetaByDni(@PathVariable String dni) {
		PersonValidator.validateDni(dni);
		List<Tarjeta> tarjetas = tarjetaRep.getAllTarjetasByDni(dni);
		List<TarjetaDTO> tarjetasDto = new ArrayList<>();
		tarjetas.forEach(tarjeta -> tarjetasDto.add(new TarjetaDTO(tarjeta)));
		log.info("GET: Se obtienen todas las tarjetas del cliente con DNI [{}]",dni);
		return tarjetasDto;
	}

	@PostMapping
	public ResponseEntity<TarjetaDTO> createTarjeta(@RequestBody CrearTarjetaDTO dto) {
		PersonValidator.validateDni(dto.getDni());
		ClienteCuenta clienteCuenta = clientCuentaRep
				.findOne(new ClienteCuentaKey(dto.getDni(), dto.getNumeroCuenta()));
		if (clienteCuenta == null) {
			log.error("POST: No existe ninguna asociacion entre el cilente con DNI [{}] y la cuenta con el número [{}])",
					dto.getDni(), dto.getNumeroCuenta());
			return ResponseEntity.notFound().build();
		}
		Integer mesCaducidad = new Random().nextInt(12) + 1;
		Integer ccv = new Random().nextInt(899) + 100;
		Integer numeroTarjeta = new Random().nextInt(8999999) + 1000000;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 2);
		Tarjeta tarjeta = new Tarjeta(numeroTarjeta.toString(), mesCaducidad, c.get(Calendar.YEAR), ccv, clienteCuenta,
				"User1");
		tarjeta = tarjetaRep.save(tarjeta);
		log.info("POST: Se ha creado la tarjeta con nº [{}] para el cliente con DNI [{}]",tarjeta.getNumeroTarjeta(),dto.getDni());
		clienteCuenta.getTarjetas().add(tarjeta);
		clientCuentaRep.save(clienteCuenta);
		return ResponseEntity.ok(new TarjetaDTO(tarjeta));
	}

	@DeleteMapping("/{numeroTarjeta}")
	public ResponseEntity<Tarjeta> deleteTarjeta(@PathVariable String numeroTarjeta) {
		Tarjeta tarjeta = tarjetaRep.findByNumeroTarjeta(numeroTarjeta);
		if (tarjeta == null) {
			log.error("DELETE: No existe ninguna tarjeta con el número [{}])",numeroTarjeta);
			return ResponseEntity.notFound().build();
		}
		tarjetaRepH.save(new TarjetaH(tarjeta, "User1"));
		tarjeta.setMcaHabilitado(false);
		tarjeta = tarjetaRep.save(tarjeta);
		log.info("DELETE: Se ha eliminado la tarjeta con nº [{}] del cliente con DNI [{}]",
				tarjeta.getNumeroTarjeta(), tarjeta.getClienteCuenta().getCliente().getDni());
		return ResponseEntity.ok(tarjeta);
	}

}
