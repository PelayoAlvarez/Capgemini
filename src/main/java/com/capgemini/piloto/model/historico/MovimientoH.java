package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.types.TipoMovimiento;

@Entity
@Table(name="MOVIMIENTO")
public class MovimientoH implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1129264172419492422L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private Double importe;

	@Enumerated(EnumType.STRING)
	private TipoMovimiento tipo;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_hora;

	private String descripcion;

	@OneToMany(mappedBy="cuenta")
	private CuentaH cuentaAsociada;

	// Campos de Auditoria
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Actua;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha_Creacion;

	private EmpleadoH empleado;

	private Boolean MCA_Habilitado;
	
	
	MovimientoH() {}

	public MovimientoH(Double importe, TipoMovimiento tipo, Date fecha, String descripcion, 
			CuentaH cuentaAsociada, Date fecha_Actua, Date fecha_Creacion, EmpleadoH empleado,
			Boolean habilitado) {
		super();
		this.importe = importe;
		this.tipo = tipo;
		this.fecha_hora = fecha;
		this.descripcion = descripcion;
		this.cuentaAsociada = cuentaAsociada;
		this.fecha_Actua = fecha_Actua;
		this.fecha_Creacion = fecha_Creacion;
		this.empleado = empleado;
		this.MCA_Habilitado = habilitado;
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
		return fecha_hora;
	}

	public void setFecha(Date fecha) {
		this.fecha_hora = fecha;
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
		return fecha_hora;
	}

	public CuentaH getCuentaAsociada() {
		return cuentaAsociada;
	}

	public EmpleadoH getEmpleado() {
		return empleado;
	}

	//Getters y Setters de Auditoria

	public Date getFecha_Actua() {
		return fecha_Actua;
	}

	public void setFecha_Actua(Date fecha_Actua) {
		this.fecha_Actua = fecha_Actua;
	}

	public Boolean getMCA_Habilitado() {
		return MCA_Habilitado;
	}

	public void setMCA_Habilitado(Boolean mCA_Habilitado) {
		MCA_Habilitado = mCA_Habilitado;
	}

	public Date getFecha_Creacion() {
		return fecha_Creacion;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cuentaAsociada == null) ? 0 : cuentaAsociada.hashCode());
		result = prime * result + ((descripcion == null) ? 0 : descripcion.hashCode());
		result = prime * result + ((fecha_hora == null) ? 0 : fecha_hora.hashCode());
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
		MovimientoH other = (MovimientoH) obj;
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
		if (fecha_hora == null) {
			if (other.fecha_hora != null)
				return false;
		} else if (!fecha_hora.equals(other.fecha_hora))
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
		return "Movimiento [id=" + id + ", importe=" + importe + ", tipo=" + tipo + ", fecha_hora=" + fecha_hora
				+ ", descripcion=" + descripcion + ", cuentaAsociada=" + cuentaAsociada + "]";
	}
}
