package com.capgemini.piloto.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

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
@CrossOrigin()
public class TarjetaController {

	@Autowired
	private TarjetaRepository tarjetaRep;
	@Autowired
	private TarjetaHRepository tarjetaRepH;
	@Autowired
	private ClienteCuentaRepository clientCuentaRep;

	@GetMapping("/{dni}")
	public List<TarjetaDTO> getTarjetaByDni(@PathVariable String dni) {
		PersonValidator.validateDni(dni);
		List<Tarjeta> tarjetas = tarjetaRep.getAllTarjetasByDni(dni);
		List<TarjetaDTO> tarjetasDto = new ArrayList<>();
		tarjetas.forEach(tarjeta -> tarjetasDto.add(new TarjetaDTO(tarjeta)));
		return tarjetasDto;
	}

	@PostMapping("")
	public ResponseEntity<Tarjeta> createTarjeta(@RequestBody CrearTarjetaDTO dto) {
		PersonValidator.validateDni(dto.getDni());
		ClienteCuenta clienteCuenta = clientCuentaRep
				.findOne(new ClienteCuentaKey(dto.getDni(), dto.getNumeroCuenta()));
		if (clienteCuenta == null)
			return ResponseEntity.notFound().build();
		Integer mesCaducidad = new Random().nextInt(12) + 1;
		Integer ccv = new Random().nextInt(899) + 100;
		Integer numeroTarjeta = new Random().nextInt(8999999) + 1000000;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, 2);
		Tarjeta tarjeta = new Tarjeta(numeroTarjeta.toString(), mesCaducidad, c.get(Calendar.YEAR), ccv, clienteCuenta,
				"User1");
		tarjeta = tarjetaRep.save(tarjeta);
		clienteCuenta.getTarjetas().add(tarjeta);
		clientCuentaRep.save(clienteCuenta);
		return ResponseEntity.ok(tarjeta);
	}

	@DeleteMapping("/{numeroTarjeta}")
	public ResponseEntity<Tarjeta> deleteTarjeta(@PathVariable String numeroTarjeta) {
		Tarjeta tarjeta = tarjetaRep.findByNumeroTarjeta(numeroTarjeta);
		if (tarjeta == null)
			return ResponseEntity.notFound().build();
		tarjetaRepH.save(new TarjetaH(tarjeta, "User1"));
		tarjeta.setMcaHabilitado(false);
		tarjeta = tarjetaRep.save(tarjeta);
		return ResponseEntity.ok(tarjeta);
	}

}
