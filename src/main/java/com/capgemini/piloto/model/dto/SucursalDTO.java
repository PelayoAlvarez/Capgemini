package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.capgemini.piloto.model.Sucursal;

public class SucursalDTO implements Serializable {

	private static final long serialVersionUID = -8890439489922274501L;
	
	private Long id;
	private String nombre;
	private String direccion;
	private Long idSucursal;
	private Set<String> dniClientes = new HashSet<>();
	private Set<String> dniEmpleados = new HashSet<>();
	
	public SucursalDTO(Sucursal sucursal) {
		super();
		this.id = sucursal.getId();
		this.nombre = sucursal.getNombre();
		this.direccion = sucursal.getDireccion();
		sucursal.getClientes().forEach(cliente -> this.dniClientes.add(cliente.getDni()));
		sucursal.getEmpleados().forEach(empleado -> this.dniClientes.add(empleado.getDni()));
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public Long getIdSucursal() {
		return idSucursal;
	}
	public void setIdSucursal(Long idSucursal) {
		this.idSucursal = idSucursal;
	}
	public Set<String> getDniClientes() {
		return dniClientes;
	}
	public void setDniClientes(Set<String> dniClientes) {
		this.dniClientes = dniClientes;
	}
	public Set<String> getDniEmpleados() {
		return dniEmpleados;
	}
	public void setDniEmpleados(Set<String> dniEmpleados) {
		this.dniEmpleados = dniEmpleados;
	}
	
	

}
