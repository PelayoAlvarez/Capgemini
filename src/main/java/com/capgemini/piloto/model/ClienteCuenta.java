package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Cliente_Cuenta")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"}, allowGetters = false)
public class ClienteCuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8783836770693903915L;

	@Id
	@Column(name = "Dni")
	private String dni;
	
	@Id
	@Column(name = "Numero_cuenta")
	private String numeroCuenta;
	
	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date fecActu;
	
	@Column(name = "Fec_creacion", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fecCreacion;
	
	@NotBlank
	@Column(name = "Usuario")
	private String usuario;
	
	@NotBlank
	@Column(name = "Mca_habilitado")
	private char mcaHabilitado;
	
	

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
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

}