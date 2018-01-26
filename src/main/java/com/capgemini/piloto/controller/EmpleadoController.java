package com.capgemini.piloto.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.dto.EmpleadoDTO;
import com.capgemini.piloto.model.historico.EmpleadoH;
import com.capgemini.piloto.repository.EmpleadoRepository;
import com.capgemini.piloto.repository.SucursalRepository;
import com.capgemini.piloto.repository.historico.EmpleadoHRepository;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private EmpleadoRepository empleadoRep;
	
	@Autowired
	private EmpleadoHRepository empleadoHRep;
	
	@Autowired
	private SucursalRepository sucursalRep;
	
	@GetMapping("/")
	public List<Empleado> getAllEmpleados() {
		logger.info("FINDALL: Se obtienen todos los empleados");
		return empleadoRep.findByMcaHabilitado();
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<Empleado> getEmpleado(@PathVariable(value = "dni") String dni) {
		logger.info("FIND: Se obtiene el empleado con DNI [{}]", dni);
		Empleado empleado = empleadoRep.findByDni(dni);
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.info("GET: No se encuentra el empleado con el DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empleado);
	}
	
	@PostMapping("/")
	public ResponseEntity<Empleado> createEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDto) {
		Empleado empleado = empleadoRep.findByDni(empleadoDto.getDni());
		if (empleado != null) {
			logger.info("CREATE: No se ha podido crear al empleado con DNI [{}] porque ya existe", empleado.getDni());
			return new ResponseEntity<>(empleado, new HttpHeaders(), HttpStatus.CONFLICT);
		}
		Sucursal sucursal = sucursalRep.findById(empleadoDto.getSucursal());
		empleado = new Empleado(empleadoDto);
		empleado.setSucursal(sucursal);
		empleado = empleadoRep.save(empleado);
		if (empleado == null) {
			logger.info("CREATE: No se ha podido crear al empleado con DNI [{}]", empleadoDto.getDni());
			return new ResponseEntity<>(empleado, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
		logger.info("CREATE: Se ha creado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}
	
	@PutMapping("/{dni}")
	public ResponseEntity<Empleado> updateEmpleado(@Valid @RequestBody EmpleadoDTO empleadoDto) {
		Empleado empleado = empleadoRep.findByDni(empleadoDto.getDni());
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.info("UPDATE: No se ha encontrado el empleado con DNI [{}]", empleadoDto.getDni());
			return ResponseEntity.notFound().build();
		}
		EmpleadoH empleadoH = new EmpleadoH(empleado, empleadoDto, empleado.getUsuario());
		Sucursal sucursal = sucursalRep.findById(empleadoDto.getSucursal());
		empleadoHRep.save(empleadoH);
		empleado.setSucursal(sucursal);
		empleado.setFecActu(new Date());
		empleadoRep.save(empleado);
		logger.info("UPDATE: Se ha actualizado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}
	
	@DeleteMapping("/{dni}")
	public ResponseEntity<Empleado> deleteEmpleado(@PathVariable(name = "dni") String dni) {
		Empleado empleado = empleadoRep.findByDni(dni);
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.info("DELETE: No se ha encontrado el empleado con DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		empleadoHRep.save(new EmpleadoH(empleado, empleado.getUsuario()));
		empleado.setMcaHabilitado(false);
		empleadoRep.save(empleado);
		logger.info("DELETE: Se ha eliminado el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}
}
