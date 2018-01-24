package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name="Empleado_H")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"fecAudit"}, allowGetters = false)
public class EmpleadoH implements Serializable {

	private static final long serialVersionUID = -4428784972707162023L;

	@Id
	@Column(name = "Dni")
	public String dni;
	
	@NotBlank
	@Column(name = "Nombre")
	public String nombre;
	
	@NotBlank
	@Column(name = "Apellidos")
	public String apellidos;
	
	@NotBlank
	@Column(name = "Direccion")
	public String direccion;
	
	@NotBlank
	@Column(name = "Fijo")
	public String fijo;
	
	@NotBlank
	@Column(name = "Movil")
	public String movil;
	
	@NotBlank
	@Column(name = "Fec_actu")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	
	@NotBlank
	@Column(name = "Fec_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	
	@NotBlank
	@Column(name = "Usuario")
	private String usuario;
	
	@NotBlank
	@Column(name = "Mca_habilitado")
	private char mcaHabilitado;
	
	@Id
	@Column(name = "Fec_audit", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fecAudit;
	
	@NotBlank
	@Column(name = "Usuario_h")
	private String usuarioH;

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

	public char getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(char mcaHabilitado) {
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
