package com.capgemini.piloto.controller;

import java.util.List;

import javax.validation.Valid;

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

import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.historico.MovimientoH;
import com.capgemini.piloto.repository.MovimientoRepository;
import com.capgemini.piloto.repository.historico.MovimientoRepositoryH;

@RestController
@RequestMapping(path="/movimiento")
public class MovimientoController {
	
	private static final String NOT_FOUND = "The requested transaction was not found";
	
	private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);
	
	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private MovimientoRepositoryH movimientoRepositoryH;
	
	@PostMapping("/movimiento")
	public Movimiento addMovimiento(@Valid @RequestBody Movimiento movimiento) {
		movimientoRepositoryH.save(new MovimientoH(movimiento));
		logger.info("Create new transaction");
		return movimientoRepository.save(movimiento);
	}
	
	@DeleteMapping("/movimiento/{id}")
	public ResponseEntity<Movimiento> removeMovimiento(@PathVariable(value = "id") Long movimientoId){
		Movimiento movimiento = movimientoRepository.findOne(movimientoId);
		if(movimiento==null) {
			logger.info(NOT_FOUND);
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
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		
		movimientoRepositoryH.save(new MovimientoH(movimiento));
		movimiento.setDescripcion(movimientoDetails.getDescripcion());
		movimiento.setFecha(movimientoDetails.getFecha());
		movimiento.setImporte(movimientoDetails.getImporte());
		movimiento.setTipo(movimiento.getTipo());
		movimiento = movimientoRepository.save(movimiento);
		logger.info("The transaction was succesfuly updated");
		return ResponseEntity.ok(movimiento);
	}
	
	@GetMapping("/movimiento/{id}")
	public ResponseEntity<Movimiento> getCuentaById(@PathVariable(value = "id") Long movimientoId) {
		Movimiento movimiento = movimientoRepository.findOne(movimientoId);
		if(movimiento == null || !movimiento.getMCA_Habilitado()) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		logger.info("The requested transaction was found");
		return ResponseEntity.ok().body(movimiento);
	}
	
	@GetMapping("/movimiento")
	public List<Movimiento> getAllCuentas() {
		logger.info("Requested every active transaction");
		return movimientoRepository.findAll();
	}
}
