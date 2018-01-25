package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Sucursal implements Serializable{

	private static final long serialVersionUID = 2511716031449738119L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private Long id;
	@Column(name = "Nombre")
	private String nombre;
	@Column(name = "Direccion")
	private String direccion;
	@Column(name = "Fec_actu")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	@Column(name = "fec_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	@Column(name = "Usuario")
	private String usuario;
	@Column(name = "Mca_habilitado")
	private Boolean mcaHabilitado;
	@OneToMany(mappedBy="sucursal")
	private Set<Cuenta> cuentas = new HashSet<>();
	@OneToMany(mappedBy = "sucursal")
	private Set<Empleado> empleados = new HashSet<>();
	@OneToMany(mappedBy = "sucursal")
	private Set<Cliente> clientes = new HashSet<>();
	
	public Sucursal() {
		//Just for JPA
	}
	
	
	public Sucursal(Long id, String nombre, String direccion, String usuario) {
		this.id = id;
		this.nombre = nombre;
		this.direccion = direccion;
		this.usuario = usuario;
		this.fecActu = this.fecCreacion = new Date();
		this.mcaHabilitado = true;
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

	public Boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(Boolean mcaHabilitado) {
		this.mcaHabilitado = mcaHabilitado;
	}
	
	
	public Set<Cuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}
	
	
	public Set<Empleado> getEmpleados() {
		return empleados;
	}


	public void setEmpleados(Set<Empleado> empleados) {
		this.empleados = empleados;
	}


	public Set<Cliente> getClientes() {
		return clientes;
	}


	public void setClientes(Set<Cliente> clientes) {
		this.clientes = clientes;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecCreacion == null) ? 0 : fecCreacion.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sucursal other = (Sucursal) obj;
		if (fecCreacion == null) {
			if (other.fecCreacion != null)
				return false;
		} else if (!fecCreacion.equals(other.fecCreacion))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sucursal [id=" + id + ", nombre=" + nombre + ", direccion=" + direccion + "]";
	}
	
	

}
