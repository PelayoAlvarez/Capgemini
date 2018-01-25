package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.capgemini.piloto.model.ClienteCuenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Cliente_Cuenta")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"}, allowGetters = false)
public class ClienteCuentaH implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 5790237128818207030L;

	@Id
	@Column(name = "Dni")
	private String dni;
	
	@Id
	@Column(name = "Numero_cuenta")
	private String numeroCuenta;
	
	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	
	@Column(name = "Fec_creacion", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	
	@NotBlank
	@Column(name = "Usuario")
	private String usuario;
	
	@NotBlank
	@Column(name = "Mca_habilitado")
	private Boolean mcaHabilitado;
	
	@Id
	@Column(name = "Fec_audit", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fecAudit;
	
	@Column(name = "Usuario_h")
	private String usuarioH;
	
	
	ClienteCuentaH() {
		// Necesario para JPA
	}
	
	public ClienteCuentaH(ClienteCuenta clienteCuenta, String usuarioH) {
		this.dni = clienteCuenta.getCliente().getDNI();
		this.numeroCuenta = clienteCuenta.getCuenta().getNumeroCuenta();
		this.fecActu = clienteCuenta.getFecActu();
		this.fecCreacion = clienteCuenta.getFecCreacion();
		this.usuario = clienteCuenta.getUsuario();
		this.mcaHabilitado = clienteCuenta.getMcaHabilitado();
		
		//auditoria
		this.fecAudit = new Date();
		this.usuarioH = usuarioH;
	}
	
	
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

	public Boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(Boolean mcaHabilitado) {
		this.mcaHabilitado = mcaHabilitado;
	}
	
	

}
