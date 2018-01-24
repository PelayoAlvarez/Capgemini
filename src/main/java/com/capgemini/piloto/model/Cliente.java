package com.capgemini.piloto.model;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="CLIENTE")
public class Cliente {
	
	@Id
	@Column(name = "Dni", nullable = false)
	private String DNI;
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	@Column(name = "Apellidos", nullable = false)
	private String apellidos;
	@Column(name = "Direccion", nullable = false)
	private String direccion;
	@Column(name = "Movil", nullable = false)
	private String movil;
	@Column(name = "Fijo", nullable = false)
	private String fijo;
	@Column(name = "Email", nullable = false)
	private String email;
	
	// Campos de Auditoria

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_actu", nullable = false)
	private Date fecha_Actua;

	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Creacion;

	@Column(name = "Usuario", nullable = false)
	private String empleado;
	
	@Column(name = "Mca_Habilitado", nullable = false)
	private Boolean MCA_Habilitado;
	
	@OneToMany
	private Set<Cuenta> cuentas = new HashSet<Cuenta>();
	
	@ManyToOne public Sucursal surcusal;
	
	public Cliente() {
		super();
	}

	public Cliente(String dNI, String nombre, String apellidos, String direccion, String movil, String fijo,
			Date fecha_Actua, Date fecha_Creacion, Empleado empleado, Boolean mCA_Habilitado, Set<Cuenta> cuentas,
			Sucursal surcusal, String email) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		this.empleado = empleado.getNombre();
		this.MCA_Habilitado = mCA_Habilitado;
		this.cuentas = cuentas;
		this.surcusal = surcusal;
		this.email = email;
	}
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDNI() {
		return DNI;
	}

	public Date getFecha_Actua() {
		return fecha_Actua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fecha_Actua = fecha_Actua;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fecha_Creacion = fecha_Creacion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Boolean getMCA_Habilitado() {
		return MCA_Habilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCA_Habilitado = mCA_Habilitado;
	}

	public Set<Cuenta> getCuentas() {
		return new HashSet<Cuenta>(cuentas);
	}
	Set<Cuenta> _getCuentas() {
		return cuentas;
	}

	protected void setCuentas(Set<Cuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Sucursal getSurcusal() {
		return surcusal;
	}

	public void setSurcusal(Sucursal surcusal) {
		this.surcusal = surcusal;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((DNI == null) ? 0 : DNI.hashCode());
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
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [ DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", movil=" + movil + ", fijo=" + fijo + ", cuentas=" + cuentas + ", surcusal=" + surcusal
				+ "]";
	}

	
}