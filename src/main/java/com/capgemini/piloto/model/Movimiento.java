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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import com.capgemini.piloto.model.types.TipoMovimiento;

@Entity
@Table(name="MOVIMIENTO")
public class Movimiento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1129264172419492422L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private Long id;
	
	@NotBlank
	@Column(name = "Importe")
	private Double importe;

	@NotBlank
	@Enumerated(EnumType.STRING)
	@Column(name = "Tipo")
	private TipoMovimiento tipo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_movimiento")
	private Date fechahora;
	
	@NotBlank
	@Column(name = "Descripcion")
	private String descripcion;

	@ManyToOne
	@Column(name = "Numero_Cuenta", nullable = false)
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
	
	@NotBlank
	@Column(name = "Usuario")
	private String usuario;
	
	@NotBlank
	@Column(name = "Mca_habilitado")
	private Boolean MCAHabilitado;
	
	
	Movimiento() {
		//Solo para jpa		
	}

	public Movimiento(Double importe, TipoMovimiento tipo, Date fecha, String descripcion, 
			Cuenta cuentaAsociada, Date fecha_Actua, Date fecha_Creacion, String usuario,
			Boolean habilitado) {
		super();
		this.importe = importe;
		this.tipo = tipo;
		this.fechahora = fecha;
		this.descripcion = descripcion;
		this.cuentaAsociada = cuentaAsociada;
		this.fechaActua = fecha_Actua;
		this.fechaCreacion = fecha_Creacion;
		this.usuario = usuario;
		this.MCAHabilitado = habilitado;
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
	
	public Date getFecha_hora() {
		return fechahora;
	}

	public Cuenta getCuentaAsociada() {
		return cuentaAsociada;
	}

	public String getUsuario() {
		return usuario;
	}

	//Getters y Setters de Auditoria

	public Date getFechaActua() {
		return fechaActua;
	}

	public void setFechaActua(Date fecha_Actua) {
		this.fechaActua = fecha_Actua;
	}

	public Boolean getMCAHabilitado() {
		return MCAHabilitado;
	}

	public void setMCAHabilitado(Boolean mCA_Habilitado) {
		MCAHabilitado = mCA_Habilitado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaAsociada == null) ? 0 : cuentaAsociada.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fechahora == null) ? 0 : fechahora.hashCode());
		result = prime * result + ((importe == null) ? 0 : importe.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		if (cuentaAsociada == null) {
			if (other.cuentaAsociada != null)
				return false;
		} else if (!cuentaAsociada.equals(other.cuentaAsociada))
			return false;
		if (descripcion == null) {
			if (other.descripcion != null)
				return false;
		} else if (!descripcion.equals(other.descripcion))
			return false;
		if (fechahora == null) {
			if (other.fechahora != null)
				return false;
		} else if (!fechahora.equals(other.fechahora))
			return false;
		if (importe == null) {
			if (other.importe != null)
				return false;
		} else if (!importe.equals(other.importe))
			return false;
		if (tipo != other.tipo)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Movimiento [id=" + id + ", importe=" + importe + ", tipo=" + tipo + ", fecha_hora=" + fechahora
				+ ", descripcion=" + descripcion + ", cuentaAsociada=" + cuentaAsociada + "]";
	}
}
