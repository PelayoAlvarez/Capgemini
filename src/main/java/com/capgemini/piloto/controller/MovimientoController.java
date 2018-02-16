package com.capgemini.piloto.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.data.export.ExportMovimientos;
import com.capgemini.piloto.errors.impl.ImporteFormatException;
import com.capgemini.piloto.errors.impl.NumeroCuentaFormatException;
import com.capgemini.piloto.errors.impl.TextoFormatException;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.dto.MisMovimientosDTO;
import com.capgemini.piloto.model.dto.MovimientoDTO;
import com.capgemini.piloto.model.historico.CuentaH;
import com.capgemini.piloto.model.historico.MovimientoH;
import com.capgemini.piloto.model.types.TipoMovimiento;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.MovimientoRepository;
import com.capgemini.piloto.repository.historico.CuentaHRepository;
import com.capgemini.piloto.repository.historico.MovimientoHRepository;
import com.capgemini.piloto.util.importe.CalcularImporte;
import com.capgemini.piloto.util.validator.ComunValidator;
import com.capgemini.piloto.util.validator.CuentaValidator;
import com.capgemini.piloto.util.validator.ImporteValidator;

@RestController
@RequestMapping(path = "/movimiento")
@CrossOrigin
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
		if(movimientoDto==null) {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.BAD_REQUEST);
		}
		
		try {
			CuentaValidator.validateCuenta(cuenta);
			ImporteValidator.validateImporte(String.valueOf(movimientoDto.getImporte()));
			ComunValidator.validateTexto(movimientoDto.getDescripcion(), "descripción", 60);
			ComunValidator.validateTexto(movimientoDto.getUsuario(), "usuario", 20);
		}
		catch(NumeroCuentaFormatException e) {
			logger.warn("NumeroCuentaFormatException", e);
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(ImporteFormatException e) {
			logger.warn("ImporteFormatException", e);
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (TextoFormatException e) {
			logger.warn("TextoFormatException", e);
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Cuenta cu = cuentaRepository.findByNumeroCuenta(cuenta);
		Movimiento movimiento = new Movimiento(movimientoDto, cu);
		
		if(cu.getImporte() >= movimiento.getImporte()) {
			movimiento = movimientoRepository.save(movimiento);
			if (movimiento == null) {
				return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			cuentaRepositoryH.save(new CuentaH(cu, cu.getUsuario()));
		}
		else {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.I_AM_A_TEAPOT);
		}

		if (movimiento.getTipo() == TipoMovimiento.ABONO) {
			cu.setImporte(cu.getImporte() + movimiento.getImporte());
		} else {
			cu.setImporte(cu.getImporte() - movimiento.getImporte());
		}
		cuentaRepository.save(cu);
		logger.info("CREATE: Se guarda el Movimiento con el id [{}]", movimiento.getId());
		return ResponseEntity.ok().body(movimiento);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Movimiento> removeMovimiento(@PathVariable(value = "id") String id) {
		Movimiento movimiento = movimientoRepository.findOne(Long.parseLong(id));
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
		
		if(movimientoDetails.getCuentaAsociada()==null) {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		try {
			ImporteValidator.validateImporte(String.valueOf(movimientoDetails.getImporte()));
			ComunValidator.validateTexto(movimientoDetails.getDescripcion(), "descripción", 60);
			CuentaValidator.validateCuenta(movimientoDetails.getCuentaAsociada().getNumeroCuenta());
			ComunValidator.validateTexto(movimientoDetails.getUsuario(), "usuario", 20);
		}
		catch(ImporteFormatException e) {
		
			logger.warn("ImporteFormatException", e);
		
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(TextoFormatException e) {
			logger.warn("TextoFormatException", e);
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch(NumeroCuentaFormatException e) {
			logger.warn("NumeroCuentaFormatException", e);
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		Movimiento movimiento = movimientoRepository.findMovimientoById(id);
		Cuenta cuenta = cuentaRepository.findByNumeroCuenta(movimiento.getCuentaAsociada().getNumeroCuenta());
		if (!movimiento.getmCAHabilitado()) {
			logger.info(NOT_FOUND);
			return ResponseEntity.notFound().build();
		}

		CalcularImporte.calcular(cuenta, movimiento, movimientoDetails);

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

	@GetMapping("/mismovimientos/{cuenta}")
	public ResponseEntity<List<MisMovimientosDTO>> getMisMovimientos(
			@PathVariable(value = "cuenta") String numeroCuenta) {		
		try {
			CuentaValidator.validateCuenta(numeroCuenta);
		}
		catch(NumeroCuentaFormatException e) {
			logger.error(e.getMessage());
			return new ResponseEntity<>(null, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
		if (cuenta != null) {
			List<Movimiento> listaMovs = movimientoRepository.findByCuentaAsociada(cuenta);
			List<MisMovimientosDTO> movimientos = new ArrayList<>();
			for (Movimiento m : listaMovs) {
				movimientos.add(new MisMovimientosDTO(m));
			}
			return new ResponseEntity<>(movimientos, new HttpHeaders(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping("/export/{cuenta}")
	public ResponseEntity<Movimiento> exportTransferencias(@PathVariable(value = "cuenta") String numeroCuenta) {
		ExportMovimientos export = new ExportMovimientos("Movimientos");
		logger.info("EXPORT: Se exportan los datos de los movimientos");
		if(export.export(getMovimientosByCuenta(numeroCuenta))) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// ---------------------------------------------------------------------------------------------------------------	
	private List<Movimiento> getMovimientosByCuenta(String numCuenta){
		return movimientoRepository.findByNumeroCuenta(numCuenta);
	}
}
