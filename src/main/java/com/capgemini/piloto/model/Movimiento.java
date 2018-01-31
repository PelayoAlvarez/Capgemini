package com.capgemini.piloto.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.capgemini.piloto.model.dto.MovimientoDTO;
import com.capgemini.piloto.model.types.TipoMovimiento;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "MOVIMIENTO")
public class Movimiento implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1129264172419492422L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;

	@Column(name = "Importe")
	private Double importe;

	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo")
	private TipoMovimiento tipo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_movimiento")
	private Date fechahora;

	@Column(name = "Descripcion")
	private String descripcion;

	@ManyToOne
	@JoinColumn(name = "Numero_Cuenta", nullable = false)
	private Cuenta cuentaAsociada;

	// Campos de Auditoria

	@Column(name = "Fec_actu", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date fechaActua;

	@Column(name = "Fec_creacion", nullable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date fechaCreacion;

	@Column(name = "Usuario")
	private String usuario;

	@Column(name = "Mca_habilitado", nullable = false)
	private Boolean mCAHabilitado;

	Movimiento() {
		// Solo para jpa
	}

	public Movimiento(Double importe, TipoMovimiento tipo, Date fecha, String descripcion, Cuenta cuentaAsociada,
			String usuario, Boolean habilitado) {
		super();
		this.importe = importe;
		this.tipo = tipo;
		this.fechahora = fecha;
		this.descripcion = descripcion;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaActua = this.fechaCreacion = new Date();
		this.usuario = usuario;
		this.mCAHabilitado = true;
	}

	public Movimiento(MovimientoDTO m, Cuenta cu) {
		super();
		this.id = m.getId();
		this.importe = m.getImporte();
		this.tipo = m.getTipo();
		this.fechahora = m.getFechahora();
		this.descripcion = m.getDescripcion();
		this.cuentaAsociada = cu;
		this.fechaActua = this.fechaCreacion = new Date();
		this.usuario = m.getUsuario();
		this.mCAHabilitado = true;
	}

	public Movimiento(MovimientoDTO mdto) {
		super();
		this.id = mdto.getId();
		this.importe = mdto.getImporte();
		this.tipo = mdto.getTipo();
		this.fechahora = mdto.getFechahora();
		this.descripcion = mdto.getDescripcion();
		this.cuentaAsociada = mdto.getCuentaAsociada();
		this.usuario = mdto.getUsuario();
		this.fechaActua = this.fechaCreacion = new Date();
		this.mCAHabilitado = true;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Date getFechahora() {
		return fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaActua() {
		return fechaActua;
	}

	public void setFechaActua(Date fechaActua) {
		this.fechaActua = fechaActua;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Boolean getmCAHabilitado() {
		return mCAHabilitado;
	}

	public void setmCAHabilitado(Boolean mCAHabilitado) {
		this.mCAHabilitado = mCAHabilitado;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Movimiento other = (Movimiento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", importe=" + importe + ", tipo=" + tipo + ", fechahora=" + fechahora
				+ ", descripcion=" + descripcion + ", cuentaAsociada=" + cuentaAsociada + ", fechaActua=" + fechaActua
				+ ", fechaCreacion=" + fechaCreacion + ", usuario=" + usuario + ", MCAHabilitado=" + mCAHabilitado
				+ "]";
	}

}
