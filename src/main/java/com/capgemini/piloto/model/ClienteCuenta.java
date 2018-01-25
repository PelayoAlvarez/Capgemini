package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.capgemini.piloto.model.types.ClienteCuentaKey;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "Cliente_Cuenta")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updateAt"}, allowGetters = false)
@IdClass(ClienteCuentaKey.class)
public class ClienteCuenta implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8783836770693903915L;

	@Id
	@ManyToOne
	private Cliente cliente;
	
	@Id
	@ManyToOne
	private Cuenta cuenta;
	
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
	private Boolean mcaHabilitado;	
	
	@OneToOne
	private Tarjeta tarjeta;
	
	
	ClienteCuenta() {		
	}
	
	public ClienteCuenta(Cliente cliente, Cuenta cuenta) {
		super();
		mcaHabilitado = true;
		Association.TitularCuenta.link(cliente, this, cuenta);
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
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

	public Boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(boolean mcaHabilitado) {
		this.mcaHabilitado = mcaHabilitado;
	}

	public Tarjeta _getTarjeta() {
		return tarjeta;
	}

	public Tarjeta getTarjeta() {
		return tarjeta;
	}
	
	public void setTarjeta(Tarjeta tarjeta) {
		this.tarjeta = tarjeta;
	}
	
	
//	public void link(Cliente cliente, Cuenta cuenta) {
//		setCliente(cliente);
//		setCuenta(cuenta);
//		cliente._getClienteCuenta().add(this);
//		cuenta._getClienteCuenta().add(this);
//	}

	public void unlinkCuenta() {
		Association.TitularCuenta.unlink(this);
	}

	public void unlinkCliente() {
		Association.TitularCliente.unlink(this,cuenta);
	
	}
}
