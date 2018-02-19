package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.types.TipoRole;

public class ClienteDTO implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -830997863021438446L;

	@NotBlank
	private String dni;

	@NotBlank
	private String nombre;

	@NotBlank
	private String apellidos;

	@NotBlank
	private String direccion;
	
	@NotBlank
	private String password;
	
	private TipoRole role;

	private String movil;

	private String fijo;

	private String email;
	
	private Set<ListarClientesCuentaDTO> cuentas = new HashSet<>();

	@NotNull
	public Long sucursal;
	
	ClienteDTO(){
		this.sucursal=1L;
	}
	
	public ClienteDTO(Cliente c) {
		this.dni = c.getDni();
		this.nombre = c.getNombre();
		this.apellidos = c.getApellidos();
		this.direccion = c.getDireccion();
		this.movil = c.getMovil();
		this.fijo = c.getFijo();
		this.email = c.getEmail();
		this.password= c.getPassword();
		this.role = c.getRole();
		for (ClienteCuenta cl : c.getClienteCuentas())
			this.getCuentas().add(new ListarClientesCuentaDTO(cl.getCuenta()));
		this.sucursal=c.getSucursal().getId();
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public TipoRole getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "ClienteDTO [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", movil=" + movil + ", fijo=" + fijo + ", email=" + email + ", cuentas=" + cuentas + ", sucursal="
				+ sucursal + "]";
	}
	
	

}