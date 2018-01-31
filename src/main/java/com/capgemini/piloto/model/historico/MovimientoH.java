package com.capgemini.piloto.model.historico;

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

import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.types.TipoMovimiento;

@Entity
@Table(name = "MOVIMIENTO_H")
public class MovimientoH implements Serializable {

	private static final long serialVersionUID = -1129264172419492422L;

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

	@Column(name = "Usuario")
	private String usuario;

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

	@Id
	@Column(name = "Fec_audit")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAudit;

	@Column(name = "Usuario_h")
	private String usuarioh;

	@Column(name = "Mca_habilitado")
	private Boolean mCAHabilitado;

	MovimientoH() {
		// Solo para jpa
	}

	public MovimientoH(Movimiento m, String usuario) {
		super();
		this.id = m.getId();
		this.importe = m.getImporte();
		this.tipo = m.getTipo();
		this.fechahora = m.getFechahora();
		this.descripcion = m.getDescripcion();
		this.usuario = m.getUsuario();
		this.cuentaAsociada = m.getCuentaAsociada();
		this.fechaActua = m.getFechaActua();
		this.fecAudit = new Date();
		this.fechaCreacion = m.getFechaCreacion();
		this.usuarioh = usuario;
		this.mCAHabilitado = m.getmCAHabilitado();
	}

	public TipoMovimiento getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimiento tipo) {
		this.tipo = tipo;
	}

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

	public Date getFecha() {
		return fechahora;
	}

	public void setFecha(Date fecha) {
		this.fechahora = fecha;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getId() {
		return id;
	}

	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}

	public String getUsuario() {
		return usuario;
	}

	// Getters y Setters de Auditoria

	public Date getFechaActua() {
		return fechaActua;
	}

	public void setFechaActua(Date fechaActua) {
		this.fechaActua = fechaActua;
	}

	public Date getFecAudit() {
		return fecAudit;
	}

	public void setFecAudit(Date fecAudit) {
		this.fecAudit = fecAudit;
	}

	public Boolean getMCAHabilitado() {
		return mCAHabilitado;
	}

	public void setMCAHabilitado(Boolean mCAHabilitado) {
		this.mCAHabilitado = mCAHabilitado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public String getUsuarioH() {
		return usuarioh;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecAudit == null) ? 0 : fecAudit.hashCode());
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
		MovimientoH other = (MovimientoH) obj;
		if (fecAudit == null) {
			if (other.fecAudit != null)
				return false;
		} else if (!fecAudit.equals(other.fecAudit))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", importe=" + importe + ", tipo=" + tipo + ", fecha_hora=" + fechahora
				+ ", descripcion=" + descripcion + ", cuentaAsociada=" + cuentaAsociada + "]";
	}
}
