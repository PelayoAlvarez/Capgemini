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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.dto.GenerarTransferenciaCuentaDTO;
import com.capgemini.piloto.model.dto.GenerarTransferenciaDTO;
import com.capgemini.piloto.model.dto.ListarTransferenciasNumeroCuentaDTO;
import com.capgemini.piloto.model.historico.CuentaH;
import com.capgemini.piloto.repository.CuentaRepository;
import com.capgemini.piloto.repository.TransferenciaRepository;
import com.capgemini.piloto.repository.historico.CuentaHRepository;

@RestController
@CrossOrigin()
@RequestMapping(path = "/transferencia")
public class TransferenciaController {

	private static final Logger logger = LoggerFactory.getLogger(TransferenciaController.class);
	@Autowired
	private TransferenciaRepository transferenciaRepository;

	@Autowired
	private CuentaRepository cuentaRepository;

	@Autowired
	private CuentaHRepository cuentaHRepository;

	// Get All Transfers
	@GetMapping("/listarTransferenciasHabilitados")
	public List<Transferencia> getAllTransferencias() {
		logger.info("Request every active transfers");
		return transferenciaRepository.findMCA();
	}

	// Create a new Transfer
	@PostMapping("/transferencia")
	public ResponseEntity<GenerarTransferenciaCuentaDTO> createTransfer(
			@RequestBody GenerarTransferenciaDTO transferencia) throws InterruptedException {
		if (transferencia == null) {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cuenta cOrigen = cuentaRepository.findOne(transferencia.getCuenta().toString());
		Cuenta cDestino = cuentaRepository.findOne(transferencia.getIdDestino());

		if (cOrigen == null || cDestino == null) {
			return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		CuentaH cOrigenH = new CuentaH(cOrigen, "pepe");
		Thread.sleep(1000);
		CuentaH cDestinoH = new CuentaH(cDestino, "pepe");
		cOrigen.setImporte(cOrigen.getImporte() - transferencia.getImporte());
		cOrigen.setFecActu(new Date());
		cDestino.setImporte(cDestino.getImporte() + transferencia.getImporte());
		cDestino.setFecActu(new Date());
		logger.info("Create new transfer");
		Transferencia transfe = transferenciaRepository.save(new Transferencia(transferencia, cOrigen));

		cuentaRepository.save(cOrigen);
		cuentaRepository.save(cDestino);
		cuentaHRepository.save(cOrigenH);
		cuentaHRepository.save(cDestinoH);
		GenerarTransferenciaCuentaDTO cOrigeDto = new GenerarTransferenciaCuentaDTO(cOrigen);
		if (transfe == null) {
			return new ResponseEntity<>(cOrigeDto, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(cOrigeDto, new HttpHeaders(), HttpStatus.OK);
	}

	// Create a new Transfer
	@GetMapping("/listarTransferenciaId/{cuenta}")
	public ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>> createTransfer(
			@PathVariable(value = "cuenta") String numeroCuenta) {
		if (numeroCuenta == null) {
			return new ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>>(null, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Cuenta cuenta = cuentaRepository.findOne(numeroCuenta);
		if (cuenta == null) {
			return new ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>>(null, new HttpHeaders(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		List<Transferencia> listaTrasnfer = transferenciaRepository.findByCuenta(cuenta);

		List<ListarTransferenciasNumeroCuentaDTO> transfers = new ArrayList<>();
		for (Transferencia transferencia : listaTrasnfer) {
			transfers.add(new ListarTransferenciasNumeroCuentaDTO(transferencia));
		}
		return new ResponseEntity<List<ListarTransferenciasNumeroCuentaDTO>>(transfers, new HttpHeaders(),
				HttpStatus.OK);
	}

}
