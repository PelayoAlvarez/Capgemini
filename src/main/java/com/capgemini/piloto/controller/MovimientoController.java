package com.capgemini.piloto.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.repository.MovimientoRepository;

@RestController
@RequestMapping(path="/movimiento")
public class MovimientoController {
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@PostMapping("/movimiento")
	public Movimiento addMovimiento(@Valid @RequestBody Movimiento movimiento) {
		return movimientoRepository.save(movimiento);
	}
	
	@DeleteMapping("/movimiento/{id}")
	public ResponseEntity<Movimiento> removeMovimiento(@PathVariable(value = "id") Long movimientoId){
		Movimiento movimiento = movimientoRepository.findOne(movimientoId);
		if(movimiento==null) {
			return ResponseEntity.notFound().build();
		}
		movimiento.setMCA_Habilitado(false);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping("/movimiento/{id}")
	public ResponseEntity<Movimiento> updateMovimeinto(@PathVariable(value = "id") Long movimientoId,
			@Valid @RequestBody Movimiento movimientoDetails) {
		Movimiento movimiento = movimientoRepository.findOne(movimientoId);
		if(movimiento == null || !movimiento.getMCA_Habilitado()) {
			return ResponseEntity.notFound().build();
		}
		movimiento.setDescripcion(movimientoDetails.getDescripcion());
		movimiento.setFecha(movimientoDetails.getFecha());
		movimiento.setImporte(movimientoDetails.getImporte());
		movimiento.setTipo(movimiento.getTipo());
		
		movimiento = movimientoRepository.save(movimiento);
		return ResponseEntity.ok(movimiento);
	}
	
	@GetMapping("/movimiento/{id}")
	public ResponseEntity<Movimiento> getCuentaById(@PathVariable(value = "id") Long movimientoId) {
		Movimiento movimiento = movimientoRepository.findOne(movimientoId);
		if(movimiento == null || !movimiento.getMCA_Habilitado()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(movimiento);
	}
	
	@GetMapping("/movimiento")
	public List<Movimiento> getAllCuentas() {
		return movimientoRepository.findAll();
	}
}
