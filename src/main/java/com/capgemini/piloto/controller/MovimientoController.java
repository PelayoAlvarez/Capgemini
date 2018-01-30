package com.capgemini.piloto.controller;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.dto.MovimientoDTO;
import com.capgemini.piloto.model.historico.CuentaH;
import com.capgemini.piloto.model.historico.MovimientoH;
import com.capgemini.piloto.model.types.TipoMovimiento;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.MovimientoRepository;
import com.capgemini.piloto.repository.historico.CuentaHRepository;
import com.capgemini.piloto.repository.historico.MovimientoHRepository;

@RestController
@RequestMapping(path = "/movimiento")
public class MovimientoController {

	private static final String NOT_FOUND = "The requested transaction was not found";

	private static final Logger logger = LoggerFactory.getLogger(CuentaController.class);

	@Autowired
	private MovimientoRepository movimientoRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	@Autowired
	private MovimientoHRepository movimientoRepositoryH;
	
	@Autowired
	private CuentaHRepository cuentaRepositoryH;

	@PostMapping("/")
	public ResponseEntity<Movimiento> addMovimiento(@RequestBody MovimientoDTO movimientoDto,
			@RequestParam String cuenta) {
		Cuenta cu = cuentaRepository.findByNumeroCuenta(cuenta);
		Movimiento movimiento = new Movimiento(movimientoDto, cu) ;
		movimiento = movimientoRepository.save(movimiento);
		if (movimiento == null) {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		cuentaRepositoryH.save(new CuentaH(cu, cu.getUsuario()));
		
		if(movimiento.getTipo() == TipoMovimiento.ABONO) {
			cu.setImporte(cu.getImporte() + movimiento.getImporte());
		}
		else {
			cu.setImporte(cu.getImporte() - movimiento.getImporte());
		}
		cuentaRepository.save(cu);
		logger.info("CREATE: Se guarda el Movimiento con el id [{}]", movimiento.getId());
		return ResponseEntity.ok().body(movimiento);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Movimiento> removeMovimiento(@PathVariable(value = "id") Long id) {
		Movimiento movimiento = movimientoRepository.findOne(id);
		if (movimiento == null) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		MovimientoH mHistorico = new MovimientoH(movimiento, movimiento.getUsuario());
		movimientoRepositoryH.save(mHistorico);
		movimiento.setmCAHabilitado(false);
		movimientoRepository.save(movimiento);
		logger.info("DELETE: Se borra el Movimiento con el id [{}]", movimiento.getId());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Movimiento> updateMovimeinto(@PathVariable(value = "id") Long id,
			@RequestBody MovimientoDTO movimientoDetails) {
		Movimiento movimiento = movimientoRepository.findMovimientoById(id);
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuentaAsociada().getNumeroCuenta());
		if (movimiento == null || !movimiento.getmCAHabilitado()) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}
		
		calcularImporte(cuenta, movimiento, movimientoDetails);
		
		movimientoRepositoryH.save(new MovimientoH(movimiento, movimiento.getUsuario()));
		movimiento.setDescripcion(movimientoDetails.getDescripcion());
		movimiento.setFechaActua(new Date());
		movimiento.setFechahora(movimientoDetails.getFechahora());
		movimiento.setImporte(movimientoDetails.getImporte());
		movimiento.setTipo(movimientoDetails.getTipo());
		movimiento.setUsuario(movimientoDetails.getUsuario());
		movimiento = movimientoRepository.save(movimiento);
		
		logger.info("UPDATE: Se actualiza el Movimiento con el id [{}]", movimiento.getId());
		
		return ResponseEntity.ok(movimiento);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Movimiento> getMovimientoById(@PathVariable(value = "id") Long id) {
		Movimiento movimiento = movimientoRepository.findOne(id);
		if (movimiento == null || !movimiento.getmCAHabilitado()) {
			logger.info("GET: No se encuentra el Movimiento con el id [{}]", id);
			return ResponseEntity.notFound().build();
		}
		logger.info("GET: Se obtiene el Movimiento con el id [{}]", id);
		return ResponseEntity.ok().body(movimiento);
	}

	@GetMapping("/")
	public List<Movimiento> getAllMovimiento() {
		logger.info("GETALL: Se obtienen todas las instancias de Movimiento");
		return movimientoRepository.findAll();
	}
	
	
	// ---------------------------------------------------------------------------------------------------------------
	
	private void calcularImporte(Cuenta cuenta, Movimiento movimiento, MovimientoDTO movimientoDetails) {
		double saldo = cuenta.getImporte();
		if(movimiento.getTipo().equals(movimientoDetails.getTipo())) {
			if(movimiento.getImporte()!=movimientoDetails.getImporte()) {
				double valor = Math.abs(movimiento.getImporte() - movimientoDetails.getImporte());
				if(movimientoDetails.getTipo() == TipoMovimiento.CARGO) {
					cuenta.setImporte(cuenta.getImporte() - valor);
				}
				else {
					cuenta.setImporte(cuenta.getImporte() + valor);
				}
			}
		}
		else {
			double valor = 0;
			if(movimiento.getImporte() != movimientoDetails.getImporte()) {
				valor = movimiento.getImporte() + movimientoDetails.getImporte();
				if(movimientoDetails.getTipo() == TipoMovimiento.ABONO) {
					cuenta.setImporte(cuenta.getImporte() + valor);
				}
				else {
					cuenta.setImporte(cuenta.getImporte() - valor);
				}
			}
			else {
				if(movimientoDetails.getTipo() == TipoMovimiento.ABONO) {
					cuenta.setImporte(cuenta.getImporte() + 2*movimientoDetails.getImporte());
				}
				else {
					cuenta.setImporte(cuenta.getImporte() - 2*movimientoDetails.getImporte());
				}
			}
		}
		
		actualizarCuenta(saldo, cuenta);
	}
	
	private void actualizarCuenta(double saldo, Cuenta cuenta) {
		if(saldo != cuenta.getImporte()) {
			CuentaH ch = new CuentaH(cuenta, cuenta.getUsuario());
			ch.setImporte(saldo);
			cuentaRepositoryH.save(ch);
			cuentaRepository.save(cuenta);
		}
	}
}
