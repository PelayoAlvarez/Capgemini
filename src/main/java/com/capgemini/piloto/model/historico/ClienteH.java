package com.capgemini.piloto.model.historico;


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

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Sucursal;

@Entity
@Table (name="CLIENTE")
public class ClienteH {
	
	@Id
	@Column(name = "Dni", nullable = false)
	private String dni;
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
	private Date fechaCreacion;

	@Column(name = "Usuario", nullable = false)
	private String empleado;
	
	@Column(name = "Mca_Habilitado", nullable = false)
	private Boolean MCAHabilitado;
	
	@Column(name = "Usuario_H", nullable = false)
	private String usuarioH;
	
	@Id
	@Column(name = "Fec_audit", nullable = false)
	private Date fecAudit;
	
	@OneToMany(mappedBy="cliente")
	private Set<ClienteCuenta> cuentas = new HashSet<ClienteCuenta>();
	
	@ManyToOne public Sucursal surcusal;
	
	public ClienteH() {
		super();
	}

	
	
	public ClienteH(String dni, String nombre, String apellidos, String direccion, String movil, String fijo,
			String email, Date fecha_Actua, Date fechaCreacion, String empleado, Boolean mCAHabilitado, String usuarioH,
			Date fecAudit, Set<ClienteCuenta> cuentas, Sucursal surcusal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.email = email;
		this.fecha_Actua = fecha_Actua;
		this.fechaCreacion = fechaCreacion;
		this.empleado = empleado;
		this.MCAHabilitado = mCAHabilitado;
		this.usuarioH = usuarioH;
		this.fecAudit = fecAudit;
		this.cuentas = cuentas;
		this.surcusal = surcusal;
	}



	public ClienteH(Cliente cliente, String empleado) {
		this.dni = cliente.getDNI();
		this.nombre = cliente.getNombre();
		this.apellidos = cliente.getApellidos();
		this.direccion = cliente.getDireccion();
		this.movil = cliente.getMovil();
		this.fijo = cliente.getFijo();
		this.fecha_Actua = cliente.getFecha_Actua();
		this.fechaCreacion = cliente.getFecha_Creacion();
		this.empleado = cliente.getEmpleado();
		this.MCAHabilitado = cliente.getMCA_Habilitado();
		this.cuentas = cliente.getClienteCuenta();
		this.surcusal = cliente.getSurcusal();
		
		this.usuarioH = empleado;
		this.fecAudit = new Date();
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
		return dni;
	}

	public Date getFecha_Actua() {
		return fecha_Actua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fecha_Actua = fecha_Actua;
	}

	public Date getFecha_Creacion() {
		return fechaCreacion;
	}

	public void setFecha_Creacion(Date fecha_Creacion) {
		this.fechaCreacion = fecha_Creacion;
	}

	public String getEmpleado() {
		return empleado;
	}

	public void setEmpleado(String empleado) {
		this.empleado = empleado;
	}

	public Boolean getMCA_Habilitado() {
		return MCAHabilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCAHabilitado = mCA_Habilitado;
	}

	public Set<ClienteCuenta> getClienteCuenta() {
		return new HashSet<ClienteCuenta>(cuentas);
	}

	protected void setCuentas(Set<ClienteCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Sucursal getSurcusal() {
		return surcusal;
	}

	public void setSurcusal(Sucursal surcusal) {
		this.surcusal = surcusal;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Boolean getMCAHabilitado() {
		return MCAHabilitado;
	}

	public void setMCAHabilitado(Boolean mCAHabilitado) {
		MCAHabilitado = mCAHabilitado;
	}

	public String getUsuarioH() {
		return usuarioH;
	}

	public void setUsuarioH(String usuarioH) {
		this.usuarioH = usuarioH;
	}

	public Date getFecAudit() {
		return fecAudit;
	}

	public void setFecAudit(Date fecAudit) {
		this.fecAudit = fecAudit;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((fecAudit == null) ? 0 : fecAudit.hashCode());
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
		if (fecAudit == null) {
			if (other.fecAudit != null)
				return false;
		} else if (!fecAudit.equals(other.fecAudit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ClienteH [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", movil=" + movil + ", fijo=" + fijo + ", email=" + email + ", fecha_Actua=" + fecha_Actua
				+ ", fechaCreacion=" + fechaCreacion + ", empleado=" + empleado + ", MCAHabilitado=" + MCAHabilitado
				+ ", usuarioH=" + usuarioH + ", fecAudit=" + fecAudit + ", cuentas=" + cuentas + ", surcusal="
				+ surcusal + "]";
	}

}