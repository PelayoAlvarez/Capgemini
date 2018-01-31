package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Sucursal;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "CLIENTE_H")
public class ClienteH implements Serializable {

	private static final long serialVersionUID = -3085799814811860133L;

	@Column(name = "Dni", nullable = false)
	private String dni;
	@Column(name = "Nombre", nullable = false)
	private String nombre;
	@Column(name = "Apellidos", nullable = false)
	private String apellidos;
	@Column(name = "Direccion", nullable = false)
	private String direccion;
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

	@Column(name = "Mca_Habilitado", nullable = false)
	private Boolean mCAHabilitado;

	@Column(name = "Usuario_H", nullable = false)
	private String usuarioH;

	@Id
	@Column(name = "Fec_audit", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAudit;

	@OneToMany(mappedBy = "cliente")
	@JsonIgnore
	private Set<ClienteCuenta> cuentas = new HashSet<>();

	@JoinColumn(name = "id_sucursal")
	@ManyToOne
	public Sucursal surcusal;

	public ClienteH() {
		super();
	}

	public ClienteH(String dni, String nombre, String apellidos, String direccion, String movil, String fijo,
			String email, Date fechaActua, Date fechaCreacion, String empleado, Boolean mCAHabilitado, String usuarioH,
			Date fecAudit, Set<ClienteCuenta> cuentas, Sucursal surcusal) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.direccion = direccion;
		this.movil = movil;
		this.fijo = fijo;
		this.email = email;
		this.fecActu = fechaActua;
		this.fecCreacion = fechaCreacion;
		this.empleado = empleado;
		this.mCAHabilitado = mCAHabilitado;
		this.usuarioH = usuarioH;
		this.fecAudit = fecAudit;
		this.cuentas = cuentas;
		this.surcusal = surcusal;
	}

	public ClienteH(Cliente cliente, String empleado) {
		this.dni = cliente.getDni();
		this.nombre = cliente.getNombre();
		this.apellidos = cliente.getApellidos();
		this.direccion = cliente.getDireccion();
		this.movil = cliente.getMovil();
		this.fijo = cliente.getFijo();
		this.fecActu = cliente.getFecActu();
		this.fecCreacion = cliente.getFecCreacion();
		this.empleado = cliente.getEmpleado();
		this.mCAHabilitado = cliente.getmCAHabilitado();
		this.cuentas = cliente.getClienteCuentas();
		this.surcusal = cliente.getSucursal();

		this.usuarioH = empleado;
		this.fecAudit = new Date();
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

	public void setFecActu(Date fecActu) {
		this.fecActu = fecActu;
	}

	public Date getFecCreacion() {
		return fecCreacion;
	}

	public void setFecCreacion(Date fecCreacion) {
		this.fecCreacion = fecCreacion;
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

	public Set<ClienteCuenta> getCuentas() {
		return cuentas;
	}

	public void setCuentas(Set<ClienteCuenta> cuentas) {
		this.cuentas = cuentas;
	}

	public Sucursal getSurcusal() {
		return surcusal;
	}

	public void setSurcusal(Sucursal surcusal) {
		this.surcusal = surcusal;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getDni() {
		return dni;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
				+ ", movil=" + movil + ", fijo=" + fijo + ", email=" + email + ", fecActu=" + fecActu + ", fecCreacion="
				+ fecCreacion + ", empleado=" + empleado + ", mCAHabilitado=" + mCAHabilitado + ", usuarioH=" + usuarioH
				+ ", fecAudit=" + fecAudit + ", cuentas=" + cuentas + ", surcusal=" + surcusal + "]";
	}

}