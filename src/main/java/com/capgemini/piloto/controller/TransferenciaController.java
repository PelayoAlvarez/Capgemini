package com.capgemini.piloto.controller;

import java.util.List;

import javax.validation.Valid;

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
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.dto.CuentaBDTO;
import com.capgemini.piloto.model.dto.GenerarTransferenciaDTO;
import com.capgemini.piloto.model.historico.TransferenciaH;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.TransferenciaRepository;
import com.capgemini.piloto.repository.historico.TransferenciaHRepository;

@RestController
@RequestMapping(path = "/transferencia")
public class TransferenciaController {
	private static final String NOT_FOUND = "The requested transfer was not found";
	
	private static final Logger logger = LoggerFactory.getLogger(TransferenciaController.class);
	@Autowired
	private TransferenciaRepository transferenciaRepository;
	
	@Autowired
	private TransferenciaHRepository transferenciaHRepository;
	
	@Autowired
	private CuentaRepository cuentaRepository;
	
	//Get All Transfers
		@GetMapping("/transferencia")
		public List<Transferencia> getAllTransferencias() {
			logger.info("Request every active transfers");
			return transferenciaRepository.findMCA();
		}
		
		/*//Create a new Transfer
		@PostMapping("/transferencia")
		public ResponseEntity<CuentaBDTO> createTransfer(@RequestBody GenerarTransferenciaDTO transferencia,
				@RequestParam String cuentaOrigen, @RequestParam String cuentaDestino, @RequestParam double importe ) {
			if(cuentaOrigen == null || cuentaDestino == null || importe <= 0 ){
				return new ResponseEntity<CuentaBDTO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Cuenta cOrigen = cuentaRepository.findOne(cuentaOrigen);
			Cuenta cDestino = cuentaRepository.findOne(cuentaDestino);
			
			
			Transferencia trans = new GenerarTransferenciaDTO(transferencia, cOrigen, cDestino, importe) ;
			cOrigen.setImporte(cOrigen.getImporte()-importe);
			cDestino.setImporte(cDestino.getImporte()+importe);
			logger.info("Create new transfer");
			trans = transferenciaRepository.save(trans);
			cuentaRepository.save(cOrigen);
			cuentaRepository.save(cDestino);
			CuentaBDTO cOrigeDto = new CuentaBDTO(cOrigen);
			if (trans == null) {
				return new ResponseEntity<CuentaBDTO>(cOrigeDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			return new ResponseEntity<CuentaBDTO>(cOrigeDto, new HttpHeaders(), HttpStatus.OK);
		}	*/
		
		//Create a new Transfer
				@PostMapping("/transferencia")
				public ResponseEntity<CuentaBDTO> createTransfer(@RequestBody GenerarTransferenciaDTO transferencia) {
					if(transferencia == null){
						return new ResponseEntity<CuentaBDTO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
					Cuenta cOrigen = cuentaRepository.findOne(transferencia.getCuenta().toString());
					Cuenta cDestino = cuentaRepository.findOne(transferencia.getIdDestino());
					
					if(cOrigen == null || cDestino ==  null){
						return new ResponseEntity<CuentaBDTO>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
					
					cOrigen.setImporte(cOrigen.getImporte()-transferencia.getImporte());
					cDestino.setImporte(cDestino.getImporte()+transferencia.getImporte());
					logger.info("Create new transfer");
					Transferencia transfe = transferenciaRepository.save(new Transferencia(transferencia, cOrigen));
					
					cuentaRepository.save(cOrigen);
					cuentaRepository.save(cDestino);
					CuentaBDTO cOrigeDto = new CuentaBDTO(cOrigen);
					if (transfe == null) {
						return new ResponseEntity<CuentaBDTO>(cOrigeDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
					}
					return new ResponseEntity<CuentaBDTO>(cOrigeDto, new HttpHeaders(), HttpStatus.OK);
				}	
		
		//Get a Single transfer
		@GetMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> getTransferenciaById(@PathVariable(value = "id") Long transferenciaId) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null || !transferencia.getMcaHabilitado()) {
				logger.info(NOT_FOUND);
				return ResponseEntity.notFound().build();
			}
			logger.info("The requested transfer was found");
			return ResponseEntity.ok().body(transferencia);
		}
		
		/*//Update a transfer
		@PutMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> updateTransferencia(@PathVariable(value = "id") Long transferenciaId,
				@Valid @RequestBody Transferencia transferenciaDetails) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null || !transferencia.getMcaHabilitado()) {
				logger.info(NOT_FOUND);
				return ResponseEntity.notFound().build();
			}
			transferenciaHRepository.save(new TransferenciaH(transferencia));
			transferencia.setCanal(transferenciaDetails.getCanal());
			transferencia.setFechaConsolidacion(transferenciaDetails.getFechaConsolidacion());
			transferencia.setFechaTransferencia(transferenciaDetails.getFechaTransferencia());
			transferencia.setIdDestino(transferencia.getIdDestino());
			transferencia.setIdOrigen(transferencia.getIdOrigen());
			transferencia.setImporte(transferencia.getImporte());
			
			Transferencia updateTransferencia = transferenciaRepository.save(transferencia);
			logger.info("The transfer was succesfully updated");
			return ResponseEntity.ok(updateTransferencia);
		}
		
		// Delete a transfer
		@DeleteMapping("/transferencia/{id}")
		public ResponseEntity<Transferencia> deleteTransferencia(@PathVariable(value = "id") Long transferenciaId) {
			Transferencia transferencia = transferenciaRepository.findOne(transferenciaId);
			if(transferencia == null || !transferencia.getMcaHabilitado()) {
				logger.info(NOT_FOUND);
				return ResponseEntity.notFound().build();
			}
			transferenciaHRepository.save(new TransferenciaH(transferencia));
			
			transferencia.setMcaHabilitado(false);
			transferenciaRepository.save(transferencia);
			logger.info("The transfer was succesfully deleted");
			return ResponseEntity.ok().build();
		}*/
}
