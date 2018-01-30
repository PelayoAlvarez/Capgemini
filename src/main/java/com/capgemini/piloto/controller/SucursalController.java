package com.capgemini.piloto.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.dto.SucursalDTO;
import com.capgemini.piloto.model.historico.SucursalH;
import com.capgemini.piloto.repository.SucursalRepository;
import com.capgemini.piloto.repository.historico.SucursalHRepository;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

	@Autowired
	private SucursalRepository sucursalRep;
	@Autowired
	private SucursalHRepository sucursalHRep;

	private Logger log = LoggerFactory.getLogger(getClass());

	@GetMapping("/")
	public List<Sucursal> getAllSucursales() {
		log.info("GETALL: Se obtienen todas las instancias de Sucursal");
		return sucursalRep.findByMcaHabilitado(true);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Sucursal> getSucursal(@PathVariable(value = "id") Long id) {
		log.info("GET: Se obtiene la Sucursal con el id [{}]", id);
		Sucursal sucursal = sucursalRep.findById(id);
		if (sucursal == null || !sucursal.getMcaHabilitado()) {
			log.info("GET: No se encuentra la Sucursal con el id [{}]", id);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(sucursal);
	}

	@PostMapping("/")
	public ResponseEntity<Sucursal> createSucursal(@RequestBody SucursalDTO sucursalDTO) {
		Sucursal savedSucursal = sucursalRep.save(new Sucursal(sucursalDTO));
		log.info("CREATE: Se guarda la Sucursal con el id [{}]", savedSucursal.getId());
		return ResponseEntity.ok(savedSucursal);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Sucursal> updateSucursal(@RequestBody SucursalDTO sucursalDTO, @PathVariable Long id) {
		Sucursal oldSucursal = sucursalRep.findById(id);
		if (oldSucursal == null) {
			log.info("UPDATE: No se ha encontrado la Sucursal con el id [{}]", id);
			return ResponseEntity.notFound().build();
		}
		sucursalHRep.save(new SucursalH(oldSucursal, oldSucursal.getNombre()));
		Sucursal sucursal = sucursalRep.save(new Sucursal(sucursalDTO));
		log.info("UPDATE: Se actualiza la Sucursal con el id [{}]", sucursalDTO.getId());
		return ResponseEntity.ok(sucursal);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Sucursal> deleteSucursal(@PathVariable(name = "id") Long id) {
		Sucursal sucursal = sucursalRep.findById(id);
		if (sucursal == null || !sucursal.getMcaHabilitado()) {
			log.info("DELETE: No se ha encontrado la Sucursal con el id [{}]", id);
			return ResponseEntity.notFound().build();
		}
		sucursal.setFecActu(new Date());
		sucursalHRep.save(new SucursalH(sucursal, sucursal.getUsuario()));
		sucursal.setMcaHabilitado(false);
		sucursalRep.save(sucursal);
		//No se si barrar Empleados, Clientes y Cuentas en cascada
		log.info("DELETE: Se borra la Sucursal con el id [{}]", sucursal.getId());
		return ResponseEntity.ok(sucursal);
	}

}
