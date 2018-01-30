package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Cliente_Cuenta_H")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"}, allowGetters = false)
public class ClienteCuentaH implements Serializable{

	private static final long serialVersionUID = 5790237128818207030L;

	@JoinColumn(name = "Dni", nullable = false)
	@ManyToOne
	private Cliente cliente;
	
	@JoinColumn(name = "Numero_cuenta", nullable = false)
	@ManyToOne
	private Cuenta cuenta;
	
	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	
	@Column(name = "Fec_creacion", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	
	@Column(name = "Usuario")
	private String usuario;
	
	@Column(name = "Mca_habilitado")
	private boolean mcaHabilitado;
	
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
		this.cliente = clienteCuenta.getCliente();
		this.cuenta = clienteCuenta.getCuenta();
		this.fecActu = clienteCuenta.getFecActu();
		this.fecCreacion = clienteCuenta.getFecCreacion();
		this.usuario = clienteCuenta.getUsuario();
		this.mcaHabilitado = clienteCuenta.getMcaHabilitado();
		
		//auditoria
		this.fecAudit = new Date();
		this.usuarioH = usuarioH;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
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
	
	

}
