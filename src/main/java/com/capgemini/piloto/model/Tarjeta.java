package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Tarjeta implements Serializable{

	private static final long serialVersionUID = 2108862824305074349L;
	
	@Id
	@NotBlank
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
	@JsonIgnore
	@JoinColumns({
        @JoinColumn(name="Dni", referencedColumnName="Dni"),
        @JoinColumn(name="Numero_cuenta", referencedColumnName="Numero_cuenta")
    })
	private ClienteCuenta clienteCuenta;
	
	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecActu;
	
	@Column(name = "Fec_creacion", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecCreacion;
	
	@NotBlank
	@Column(name = "Usuario")
	private String usuario;
	
	@Column(name = "Mca_habilitado", nullable = false)
	private boolean mcaHabilitado;
	
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

	public boolean getMcaHabilitado() {
		return mcaHabilitado;
	}

	public void setMcaHabilitado(boolean mcaHabilitado) {
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
