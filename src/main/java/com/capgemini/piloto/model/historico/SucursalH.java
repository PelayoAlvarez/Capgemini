package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "Sucursal_H")
public class SucursalH implements Serializable {

	private static final long serialVersionUID = 2511716031449738119L;

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
	@OneToMany(mappedBy = "sucursal")
	@JsonIgnore
	private Set<Empleado> empleados = new HashSet<>();
	@OneToMany(mappedBy = "sucursal")
	@JsonIgnore
	private Set<Cliente> clientes = new HashSet<>();
	@Column(name = "Fec_audit")
	@Temporal(TemporalType.TIMESTAMP)
	@Id
	private Date fecAudit;
	@Column(name = "Usuario_h")
	private String usuarioH;

	public SucursalH() {
		//Just for JPA
	}

	public SucursalH(Sucursal sucursal, String usuarioH) {
		super();
		this.id = sucursal.getId();
		this.nombre = sucursal.getNombre();
		this.direccion = sucursal.getDireccion();
		this.fecActu = sucursal.getFecActu();
		this.fecCreacion = sucursal.getFecCreacion();
		this.usuario = sucursal.getUsuario();
		this.usuarioH = usuarioH;
		this.fecAudit = new Date();
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

	public Date getFecAudit() {
		return fecAudit;
	}

	public void setFecAudit(Date fecAudit) {
		this.fecAudit = fecAudit;
	}

	public String getUsuarioH() {
		return usuarioH;
	}

	public void setUsuarioH(String usuarioH) {
		this.usuarioH = usuarioH;
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
		result = prime * result + ((fecAudit == null) ? 0 : fecAudit.hashCode());
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
		SucursalH other = (SucursalH) obj;
		if (fecAudit == null) {
			if (other.fecAudit != null)
				return false;
		} else if (!fecAudit.equals(other.fecAudit))
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
		return "SucursalH [id=" + id + ", nombre=" + nombre + ", fecAudit=" + fecAudit + "]";
	}
}
