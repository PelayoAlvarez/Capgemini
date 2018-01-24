package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.Empleado;

@Entity
@Table(name="Empleado_H")
public class EmpleadoH implements Serializable {

	private static final long serialVersionUID = -4428784972707162023L;

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
	
	@Column(name = "Email")
	public String email;
	
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
	
	@Id
	@Column(name = "Fec_audit")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAudit;
	
	@Column(name = "Usuario_h")
	private String usuarioH;

	public EmpleadoH() { };
	
	public EmpleadoH(Empleado empleado, String usuarioH) {
		super();
		this.dni = empleado.getDni();
		this.nombre = empleado.getNombre();
		this.apellidos = empleado.getApellidos();
		this.direccion = empleado.getDireccion();
		this.fijo = empleado.getFijo();
		this.movil = empleado.getMovil();
		this.email = empleado.getEmail();
		this.fecActu = empleado.getFecActu();
		this.fecCreacion = empleado.getFecCreacion();
		this.usuario = empleado.getUsuario();
		this.fecAudit = new Date();
		this.usuarioH = usuarioH;
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
}
