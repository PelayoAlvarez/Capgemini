package com.capgemini.piloto.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.data.export.ExportEmpleados;
import com.capgemini.piloto.errors.impl.DniFormatException;
import com.capgemini.piloto.errors.impl.EmailFormatException;
import com.capgemini.piloto.errors.impl.TelefonoFormatException;
import com.capgemini.piloto.errors.impl.TextoFormatException;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.dto.EmpleadoDTO;
import com.capgemini.piloto.model.historico.EmpleadoH;
import com.capgemini.piloto.repository.EmpleadoRepository;
import com.capgemini.piloto.repository.SucursalRepository;
import com.capgemini.piloto.repository.historico.EmpleadoHRepository;
import com.capgemini.piloto.util.validator.PersonValidator;

@RestController
@RequestMapping("/empleado")
@CrossOrigin
public class EmpleadoController {

	private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);

	@Autowired
	private EmpleadoRepository empleadoRep;

	@Autowired
	private EmpleadoHRepository empleadoHRep;

	@Autowired
	private SucursalRepository sucursalRep;
	
	private static final String DNI = "DNI incorrecto";

	@GetMapping("/")
	public List<Empleado> getAllEmpleados() {
		logger.info("FINDALL: Se obtienen todos los empleados");
		return empleadoRep.findByMcaHabilitado();
	}

	@GetMapping("/{dni}")
	public ResponseEntity<Empleado> getEmpleado(@PathVariable(value = "dni") String dni) {
		try {
			PersonValidator.validateDni(dni);
		}
		catch (DniFormatException e) {
			logger.error(DNI + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("FIND: El DNI [{}] es válido", dni);
		Empleado empleado = empleadoRep.findByDni(dni);
		logger.info("FIND: Se obtiene el empleado con DNI [{}]", dni);
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.error("FIND: No se encuentra el empleado con DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empleado);
	}

	@PostMapping("/")
	public ResponseEntity<Empleado> createEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDto) {
		try {
			validarEmpleado(empleadoDto);
		}
		catch (DniFormatException e) {
			logger.error( DNI + e.getMessage());
			return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (TextoFormatException e) {
			logger.error("Texto incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null,  HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (TelefonoFormatException e) {
			logger.error("Teléfono incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (EmailFormatException e) {
			logger.error("Email incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("CREATE: Los datos del empleado son válidos");
		Empleado empleado = empleadoRep.findByDni(empleadoDto.getDni());
		if (empleado != null && empleado.getMcaHabilitado()) {
			logger.error("CREATE: No se ha podido crear al empleado con DNI [{}] porque ya existe", empleado.getDni());
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		} else if (empleado != null && !empleado.getMcaHabilitado()) { 
			empleado.setMcaHabilitado(true);
			logger.info("CREATE: El empleado con DNI [{}] ha sido habilitado porque ya existía pero estaba deshabilitado", empleado.getDni());
		}
		Sucursal sucursal = sucursalRep.findById(empleadoDto.getSucursal());
		if (sucursal == null || !sucursal.getMcaHabilitado()) {
			logger.error("CREATE: La sucursal de ID [{}] no existe", empleadoDto.getSucursal());
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		empleado = new Empleado(empleadoDto);
		empleado.setSucursal(sucursal);
		empleado = empleadoRep.save(empleado);
		if (empleado == null) {
			logger.error("CREATE: No se ha podido crear al empleado con DNI [{}]", empleadoDto.getDni());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CREATE: Se ha creado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}

	@PutMapping("/")
	public ResponseEntity<Empleado> updateEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDto) {
		try {
			validarEmpleado(empleadoDto);
		}
		catch (DniFormatException e) {
			logger.error(DNI + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (TextoFormatException e) {
			logger.error("Texto incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (TelefonoFormatException e) {
			logger.error("Teléfono incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (EmailFormatException e) {
			logger.error("Email incorrecto: " + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		logger.info("UPDATE: Los datos del empleado son válidos");
		Empleado empleado = empleadoRep.findByDni(empleadoDto.getDni());
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.error("UPDATE: El empleado con DNI [{}] no existe", empleadoDto.getDni());
			return ResponseEntity.notFound().build();
		}
		empleado = new Empleado(empleadoDto);
		Sucursal sucursal = sucursalRep.findById(empleadoDto.getSucursal());
		if (sucursal == null || !sucursal.getMcaHabilitado()) {
			logger.error("UPDATE: La sucursal de ID [{}] no existe", empleadoDto.getSucursal());
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
		empleado.setSucursal(sucursal);
		EmpleadoH empleadoH = new EmpleadoH(empleado, empleadoDto, empleado.getUsuario());
		empleadoH = empleadoHRep.save(empleadoH);
		if (empleadoH == null) {
			logger.error("UPDATE: No se ha podido guardar la operación en la tabla histórica");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		empleado.setFecActu(new Date());
		empleado = empleadoRep.save(empleado);
		if (empleado == null) {
			logger.error("UPDATE: No se ha podido actualizar al empleado con DNI [{}]", empleadoDto.getDni());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("UPDATE: Se ha actualizado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}

	@DeleteMapping("/{dni}")
	public ResponseEntity<Empleado> deleteEmpleado(@PathVariable(name = "dni") String dni) {
		try {
			PersonValidator.validateDni(dni);
		}
		catch (DniFormatException e) {
			logger.error(DNI + e.getMessage());
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("FIND: El DNI [{}] es válido", dni);
		Empleado empleado = empleadoRep.findByDni(dni);
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.error("DELETE: No se ha encontrado el empleado con DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		EmpleadoH empleadoH = new EmpleadoH(empleado, empleado.getUsuario());
		empleadoH = empleadoHRep.save(empleadoH);
		if (empleadoH == null) {
			logger.error("UPDATE: No se ha podido guardar la operación en la tabla histórica");
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		empleado.setMcaHabilitado(false);
		empleado = empleadoRep.save(empleado);
		if (empleado == null) {
			logger.error("DELETE: No se ha podido eliminar al empleado con DNI [{}]", dni);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("DELETE: Se ha eliminado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}
	
	@GetMapping("/export")
	public ResponseEntity<Empleado> exportEmpleados() {
		ExportEmpleados export = new ExportEmpleados("empleados");
		logger.info("EXPORT: Se exportan los datos de los empleados");
		if(export.export(getAllEmpleados())) {
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	private void validarEmpleado(EmpleadoDTO empleado) {
		PersonValidator.validateDni(empleado.getDni());
		PersonValidator.validateNombre(empleado.getNombre());
		PersonValidator.validateApellidos(empleado.getApellidos());
		PersonValidator.validateDireccion(empleado.getDireccion());
		if (empleado.getFijo() != null && !empleado.getFijo().equals("")) PersonValidator.validateTelefonoFijo(empleado.getFijo());
		if (empleado.getMovil() != null && !empleado.getMovil().equals("")) PersonValidator.validateTelefonoMovil(empleado.getMovil());
		if (empleado.getEmail() != null && !empleado.getEmail().equals("")) PersonValidator.validateEmail(empleado.getEmail());
		PersonValidator.validatePassword(empleado.getPassword());
	}
}
