package com.capgemini.piloto.model.historico;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table (name="CLIENTE")
public class ClienteH {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
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

	private EmpleadoH empleado;

	private Boolean MCA_Habilitado;
	
	@OneToMany
	private Set<CuentaH> cuentas = new HashSet<CuentaH>();
	
	@ManyToOne public SucursalH surcusal;
	
	public ClienteH() {
		super();
	}

	public ClienteH(String dNI, String nombre, String apellidos, String direccion, String movil, String fijo,
			Date fecha_Actua, Date fecha_Creacion, EmpleadoH empleado, Boolean mCA_Habilitado, Set<CuentaH> cuentas,
			SucursalH surcusal) {
		super();
		DNI = dNI;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		this.empleado = empleado;
		this.MCA_Habilitado = mCA_Habilitado;
		this.cuentas = cuentas;
		this.surcusal = surcusal;
	}
	
	

	public Long getId() {
		return id;
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

	public EmpleadoH getEmpleado() {
		return empleado;
	}

	public void setEmpleado(EmpleadoH empleado) {
		this.empleado = empleado;
	}

	public Boolean getMCA_Habilitado() {
		return MCA_Habilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCA_Habilitado = mCA_Habilitado;
	}

	public Set<CuentaH> getCuentas() {
		return new HashSet<CuentaH>(cuentas);
	}

	protected void setCuentas(Set<CuentaH> cuentas) {
		this.cuentas = cuentas;
	}

	public SucursalH getSurcusal() {
		return surcusal;
	}

	public void setSurcusal(SucursalH surcusal) {
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
		ClienteH other = (ClienteH) obj;
		if (DNI == null) {
			if (other.DNI != null)
				return false;
		} else if (!DNI.equals(other.DNI))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", DNI=" + DNI + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", movil=" + movil + ", fijo=" + fijo + ", cuentas=" + cuentas + ", surcusal=" + surcusal
				+ "]";
	}

	
}