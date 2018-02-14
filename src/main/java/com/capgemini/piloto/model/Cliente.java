package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.dto.ClienteDTO;
import com.capgemini.piloto.model.types.TipoRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENTE")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4393419578690740710L;
	@Id
	@Column(name = "Dni", nullable = false)
	private String dni;
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	@Column(name = "Apellidos", nullable = false)
	private String apellidos;
	@Column(name = "Direccion", nullable = false)
	private String direccion;
	@Column(name = "Password", nullable = false)
	private String password;
	@Enumerated(EnumType.STRING)
	@Column(name = "Role")
	private TipoRole role;
	@Column(name = "Movil")
	private String movil;
	@Column(name = "Fijo")
	private String fijo;
	@Column(name = "Email")
	private String email;

	// Campos de Auditoria

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_actu", nullable = false)
	private Date fecActu;

	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;

	@Column(name = "Usuario", nullable = false)
	private String empleado;

	@Column(name = "Mca_habilitado", nullable = false)
	private Boolean mCAHabilitado;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private Set<ClienteCuenta> clienteCuenta = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "id_sucursal")
	private Sucursal sucursal;

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public Cliente() {
		super();
	}

	public Cliente(ClienteDTO cliente, Sucursal sucursal) {
		super();
		this.dni = cliente.getDni();
		this.nombre = cliente.getNombre();
		this.apellidos = cliente.getApellidos();
		this.direccion = cliente.getDireccion();
		this.movil = cliente.getMovil();
		this.fijo = cliente.getFijo();
		this.email = cliente.getEmail();
		this.fecActu = new Date();
		this.fecCreacion = new Date();
		this.empleado = "Pepe";
		this.mCAHabilitado = true;
		this.password = cliente.getPassword();
		this.role = TipoRole.USER;
		this.sucursal = sucursal;
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

	public Date getFecActu() {
		return fecActu;
	}

	public void setFecActu(Date fechaActua) {
		this.fecActu = fechaActua;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fechaCreacion) {
		this.fecCreacion = fechaCreacion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Boolean getmCAHabilitado() {
		return mCAHabilitado;
	}

	public void setmCAHabilitado(Boolean mCAHabilitado) {
		this.mCAHabilitado = mCAHabilitado;
	}

	public Set<ClienteCuenta> getClienteCuentas() {
		return new HashSet<>(clienteCuenta);
	}


	Set<ClienteCuenta> pgetClienteCuentas() {
		return clienteCuenta;
	}

	public void setClienteCuentas(Set<ClienteCuenta> cuentas) {
		this.clienteCuenta = cuentas;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
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
		Cliente other = (Cliente) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", password=" + password + ", movil=" + movil + ", fijo=" + fijo + ", email=" + email + ", fecActu="
				+ fecActu + ", fecCreacion=" + fecCreacion + ", empleado=" + empleado + ", mCAHabilitado="
				+ mCAHabilitado + ", clienteCuenta=" + clienteCuenta + ", sucursal=" + sucursal + "]";
	}


}