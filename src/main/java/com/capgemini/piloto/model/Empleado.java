package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Empleado implements Serializable {

	private static final long serialVersionUID = -6798286537097547476L;

	@Id
	@Column(name = "Dni")
	public String dni;
	@Column(name = "Nombre")
	public String nombre;
	@Column(name = "Apellidos")
	public String apellidos;
	@Column(name = "Direccion")
	public String direccion;
	@Column(name = "Fijo")
	public String fijo;
	@Column(name = "Movil")
	public String movil;
	@Column(name = "Fec_actu")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	@Column(name = "Fec_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	@Column(name = "Usuario")
	private String usuario;
	@Column(name = "Mca_habilitado")
	private boolean mcaHabilitado;

	public Empleado() { };
	
	public Empleado(String dni, String nombre, String apellidos, String direccion, String fijo, String movil, String usuario) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.fijo = fijo;
		this.movil = movil;
		this.fecActu = this.fecCreacion = new Date();
		this.usuario = usuario;
		this.mcaHabilitado = true;
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

	public Date getFecActu() {
		return fecActu;
	}

	public void setFecActu(Date fecActu) {
		this.fecActu = fecActu;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(boolean mcaHabilitado) {
		this.mcaHabilitado = mcaHabilitado;
	}

}
