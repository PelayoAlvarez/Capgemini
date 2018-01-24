package com.capgemini.piloto.controller;

import java.util.Date;
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

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.historico.EmpleadoH;
import com.capgemini.piloto.repository.EmpleadoRepository;
import com.capgemini.piloto.repository.historico.EmpleadoHRepository;

@RestController
@RequestMapping("/empleado")
public class EmpleadoController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmpleadoController.class);
	
	@Autowired
	private EmpleadoRepository empleadoRep;
	
	@Autowired
	private EmpleadoHRepository empleadoHRep;
	
	@GetMapping("/")
	public List<Empleado> getAllEmpleados() {
		logger.info("FINDALL: Se obtienen todos los empleados");
		return empleadoRep.findByMcaModificado(true);
	}
	
	@GetMapping("/{dni}")
	public ResponseEntity<Empleado> getEmpleado(@PathVariable(value = "dni") String dni) {
		logger.info("FIND: Se obtiene el empleado con DNI [{}]", dni);
		Empleado empleado = empleadoRep.findByDni(dni);
		if (empleado == null) {
			logger.info("GET: No se encuentra el empleado con el DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(empleado);
	}
	
	@PostMapping("/")
	public ResponseEntity<Empleado> createEmpleado(@RequestBody Empleado empleado) {
		Empleado nuevoEmpleado = empleadoRep.save(empleado);
		logger.info("CREATE: Se guarda el empleado con DNI [{}]", nuevoEmpleado.getDni());
		return ResponseEntity.ok(nuevoEmpleado);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Empleado> updateEmpleado(@RequestBody Empleado empleado) {
		Empleado antiguoEmpleado = empleadoRep.findByDni(empleado.getDni());
		if (antiguoEmpleado == null || !empleado.getMcaHabilitado()) {
			logger.info("UPDATE: No se ha encontrado el empleado con DNI [{}]", empleado.getDni());
			return ResponseEntity.notFound().build();
		}
		antiguoEmpleado.setFecActu(new Date());
		empleadoHRep.save(new EmpleadoH(antiguoEmpleado, antiguoEmpleado.getUsuario()));
		empleadoRep.save(empleado);
		logger.info("UPDATE: Se actualiza el empleado con DNI [{}]", empleado.getDni());
		return ResponseEntity.ok(empleado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Empleado> deleteEmpleado(@PathVariable(name = "dni") String dni) {
		Empleado empleado = empleadoRep.findByDni(dni);
		if (empleado == null || !empleado.getMcaHabilitado()) {
			logger.info("DELETE: No se ha encontrado el empleado con DNI [{}]", dni);
			return ResponseEntity.notFound().build();
		}
		empleadoHRep.save(new EmpleadoH(empleado, empleado.getUsuario()));
		empleado.setMcaHabilitado(false);
		logger.info("DELETE: Se borra el empleado con DNI [{}]", empleado.getDni());
		empleadoRep.save(empleado);
		return ResponseEntity.ok(empleado);
	}
}
