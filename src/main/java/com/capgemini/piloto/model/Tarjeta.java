package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 2108862824305074349L;
	
	@Id
	@NotNull
	@Column(name="Numero_tarjeta")
	private String numeroTarjeta;
	@NotNull
	@Column(name="Mes_caducidad")
	private Integer mesCaducidad;
	@NotNull
	@Column(name="Anyo_caducidad")
	private Integer anyoCaducidad;
	@NotNull
	@Column(name="Ccv")
	private Integer ccv;
	@OneToOne
	private ClienteCuenta clienteCuenta;
	@Column(name = "Fec_actu")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	@Column(name = "fec_creacion")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	@Column(name = "Usuario")
	private String usuario;
	@Column(name = "Mca_habilitado")
	private Boolean mcaHabilitado;
	
	public Tarjeta() {
		//Just for JPA
	}

	public Tarjeta(String numeroTarjeta, Integer mesCaducidad, Integer anyoCaducidad, Integer ccv,
			ClienteCuenta clienteCuenta, String usuario) {
		super();
		this.numeroTarjeta = numeroTarjeta;
		this.mesCaducidad = mesCaducidad;
		this.anyoCaducidad = anyoCaducidad;
		this.ccv = ccv;
		this.clienteCuenta = clienteCuenta;
		this.usuario = usuario;
	}

	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	public Integer getMesCaducidad() {
		return mesCaducidad;
	}

	public void setMesCaducidad(Integer mesCaducidad) {
		this.mesCaducidad = mesCaducidad;
	}

	public Integer getAnyoCaducidad() {
		return anyoCaducidad;
	}

	public void setAnyoCaducidad(Integer anyoCaducidad) {
		this.anyoCaducidad = anyoCaducidad;
	}

	public Integer getCcv() {
		return ccv;
	}

	public void setCcv(Integer ccv) {
		this.ccv = ccv;
	}

	public ClienteCuenta getClienteCuenta() {
		return clienteCuenta;
	}

	public void setClienteCuenta(ClienteCuenta clienteCuenta) {
		this.clienteCuenta = clienteCuenta;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroTarjeta == null) ? 0 : numeroTarjeta.hashCode());
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
		Tarjeta other = (Tarjeta) obj;
		if (numeroTarjeta == null) {
			if (other.numeroTarjeta != null)
				return false;
		} else if (!numeroTarjeta.equals(other.numeroTarjeta))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Tarjeta [numeroTarjeta=" + numeroTarjeta + ", mesCaducidad=" + mesCaducidad + ", anyoCaducidad="
				+ anyoCaducidad + "]";
	}
	
	

}
