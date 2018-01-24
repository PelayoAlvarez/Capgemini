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

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;

@Entity
@Table (name="CLIENTE")
public class ClienteH {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	public String dni;
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
	
	public ClienteH() {
		super();
	}

	public ClienteH(String dni, String nombre, String apellidos, String direccion, String movil, String fijo,
			Date fecha_Actua, Date fecha_Creacion, Empleado empleado, Boolean MCA_Habilitado, Set<Cuenta> cuentas,
			Sucursal surcusal) {
		super();
		dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		this.empleado = empleado;
		this.MCA_Habilitado = MCA_Habilitado;
		this.cuentas = cuentas;
		this.surcusal = surcusal;
	}
	
	public ClienteH(Cliente cliente) {
		dni = dni;
		this.nombre = cliente.nombre;
		this.apellidos = cliente.apellidos;
		this.direccion = cliente.direccion;
		this.movil = cliente.movil;
		this.fijo = cliente.fijo;
		this.fecha_Actua = cliente.getFecha_Actua();
		this.fecha_Creacion = cliente.getFecha_Creacion();
		this.empleado = cliente.getEmpleado();
		this.MCA_Habilitado = cliente.getMCA_Habilitado();
		this.cuentas = cliente.getCuentas();
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
		return dni;
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
		ClienteH other = (ClienteH) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cliente [id=" + id + ", DNI=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion="
				+ direccion + ", movil=" + movil + ", fijo=" + fijo + ", cuentas=" + cuentas + ", surcusal=" + surcusal
				+ "]";
	}

	
}