package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Tarjeta;

public class TarjetaH implements Serializable{


	private static final long serialVersionUID = 8218081642759854455L;
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
	@Column(name = "Fec_audit")
	@Temporal(TemporalType.TIMESTAMP)
	@Id
	private Date fecAudit;
	@Column(name = "Usuario_h")
	private String usuarioH;
	
	public TarjetaH() {
		//Just for JPA
	}

	public TarjetaH(Tarjeta tarjeta, String usuario) {
		super();
		this.numeroTarjeta = tarjeta.getNumeroTarjeta();
		this.mesCaducidad = tarjeta.getMesCaducidad();
		this.anyoCaducidad = tarjeta.getAnyoCaducidad();
		this.ccv = tarjeta.getCcv();
		this.clienteCuenta = tarjeta.getClienteCuenta();
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((numeroTarjeta == null) ? 0 : numeroTarjeta.hashCode());
		result = prime * result + ((usuarioH == null) ? 0 : usuarioH.hashCode());
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
		TarjetaH other = (TarjetaH) obj;
		if (numeroTarjeta == null) {
			if (other.numeroTarjeta != null)
				return false;
		} else if (!numeroTarjeta.equals(other.numeroTarjeta))
			return false;
		if (usuarioH == null) {
			if (other.usuarioH != null)
				return false;
		} else if (!usuarioH.equals(other.usuarioH))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TarjetaH [numeroTarjeta=" + numeroTarjeta + ", fecAudit=" + fecAudit + ", usuarioH=" + usuarioH + "]";
	}


}
