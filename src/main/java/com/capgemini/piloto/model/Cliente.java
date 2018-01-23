package com.capgemini.piloto.model;

<<<<<<< HEAD

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
	public String DNI;
	public String nombre;
	public String apellidos;
	public String direccion;
	public String movil;
	public String fijo;
	
	// Campos de Auditoria

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Actua;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Creacion;

	private Empleado empleado;

	private Boolean MCA_Habilitado;
	
	@OneToMany
	private Set<Cuenta> cuentas = new HashSet<Cuenta>();
	
	@ManyToOne public Sucursal surcusal;
	
	public Cliente() {
		super();
	}

	public Cliente(String dNI, String nombre, String apellidos, String direccion,
			String movil, String fijo) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
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

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
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
		result = prime * result + ((direccion == null) ? 0 : direccion.hashCode());
		result = prime * result + ((apellidos == null) ? 0 : apellidos.hashCode());
		result = prime * result + ((fijo == null) ? 0 : fijo.hashCode());
		result = prime * result + ((movil == null) ? 0 : movil.hashCode());
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
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
		if (direccion == null) {
			if (other.direccion != null)
				return false;
		} else if (!direccion.equals(other.direccion))
			return false;
		if (apellidos == null) {
			if (other.apellidos != null)
				return false;
		} else if (!apellidos.equals(other.apellidos))
			return false;
		if (fijo == null) {
			if (other.fijo != null)
				return false;
		} else if (!fijo.equals(other.fijo))
			return false;
		if (movil == null) {
			if (other.movil != null)
				return false;
		} else if (!movil.equals(other.movil))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
=======
import java.io.Serializable;

public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6790560583600708557L;
>>>>>>> afb2c17bc1d3cb0b129de2a657dffd635cf37450

	@Override
	public String toString() {
		return "Cliente [DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", Direccion=" + direccion
				+ ", movil=" + movil + ", fijo=" + fijo + "]";
	}
	
}