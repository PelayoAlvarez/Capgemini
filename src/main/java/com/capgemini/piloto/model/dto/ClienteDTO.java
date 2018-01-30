package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;

public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String dni;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellidos;

	@NotBlank
	private String direccion;

	private String movil;

	private String fijo;

	private String email;

	private Set<ListarClientesCuentaDTO> cuentas = new HashSet<ListarClientesCuentaDTO>();

	@NotNull
	public Long sucursal;

	public ClienteDTO(Cliente c) {
		this.dni = c.getDni();
		this.nombre = c.getNombre();
		this.apellidos = c.getApellidos();
		this.direccion = c.getDireccion();
		this.movil = c.getMovil();
		this.fijo = c.getFijo();
		this.email = c.getEmail();
		for (ClienteCuenta cl : c.getClienteCuentas())
			this.getCuentas().add(new ListarClientesCuentaDTO(cl.getCuenta()));
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

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Set<ListarClientesCuentaDTO> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<ListarClientesCuentaDTO> cuentas) {
		this.cuentas = cuentas;
	}

	public Long getSucursal() {
		return sucursal;
	}

	public void setSucursal(Long sucursal) {
		this.sucursal = sucursal;
	}

	public String getDni() {
		return dni;
	}

}