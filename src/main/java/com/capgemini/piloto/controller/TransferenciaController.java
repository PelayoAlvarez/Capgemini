package com.capgemini.piloto.controller;

import java.util.ArrayList;
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
import com.capgemini.piloto.model.dto.ListarTransferenciasNumeroCuentaDTO;
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
		@GetMapping("/listarTransferenciasHabilitados")
		public List<Transferencia> getAllTransferencias() {
			logger.info("Request every active transfers");
			return transferenciaRepository.findMCA();
		}
		
		
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
		
		//Create a new Transfer
		@GetMapping("/listarTransferenciaId/{cuenta}")
		public ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>> createTransfer(@PathVariable(value = "cuenta") String numeroCuenta) {
			if(numeroCuenta == null){
				return new ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
			}
			Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
			List<Transferencia> listaTrasnfer = transferenciaRepository.findByCuenta(cuenta);
			
			List<ListarTransferenciasNumeroCuentaDTO> transfers = new ArrayList<>();
			for (Transferencia transferencia : listaTrasnfer) {
				transfers.add(new ListarTransferenciasNumeroCuentaDTO(transferencia));
			}
			return new ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>>(transfers, new HttpHeaders(), HttpStatus.OK);
		}	
		
}
