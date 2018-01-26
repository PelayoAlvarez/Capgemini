package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Transferencia;

public class EmpleadoDTO implements Serializable {

	private static final long serialVersionUID = 7180975093272384810L;
	
	@NotBlank
	public String dni;
	
	@NotBlank
	public String nombre;
	
	@NotBlank
	public String apellidos;
	
	@NotBlank
	public String direccion;
	
	public String fijo;
	
	public String movil;
	
	public String email;
	
	@NotBlank
	public String usuario;
	
	private Set<Long> transferencias = new HashSet<>();
	
	@NotNull
	public Long sucursal;
	
	public EmpleadoDTO() { }
	
	public EmpleadoDTO(Empleado empleado) {
		this.dni = empleado.getDni();
		this.nombre = empleado.getNombre();
		this.apellidos = empleado.getApellidos();
		this.direccion = empleado.getDireccion();
		this.fijo = empleado.getFijo();
		this.movil = empleado.getMovil();
		this.email = empleado.getEmail();
		this.usuario = empleado.getUsuario();
		this.transferencias = empleado.getTransferencias().stream().map(Transferencia::getId).collect(Collectors.toSet());
		this.sucursal = empleado.getSucursal().getId();
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Set<Long> getTransferencias() {
		return transferencias;
	}

	public void setTransferencias(Set<Long> transferencias) {
		this.transferencias = transferencias;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}
}
