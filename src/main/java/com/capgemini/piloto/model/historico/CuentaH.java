package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.capgemini.piloto.model.Cliente;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.Transferencia;

@Entity
@Table(name = "CUENTAH")
public class CuentaH implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7283533209815501984L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "Numero_cuenta")
	private String numeroCuenta;

	@ManyToOne
	private Sucursal sucursal;

	@Column(name = "Usuario")
	private String usuario;

	@OneToMany
	private Set<Cliente> clientes = new HashSet<>();

	@OneToMany
	private Set<Movimiento> movimientos = new HashSet<>();

	@OneToMany(mappedBy = "cuenta")
	private Set<Transferencia> transferencias = new HashSet<>();

	// Campos de Auditoria

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_actu")
	private Date fecActu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_audit")
	private Date fecAudit;

	@Column(name = "Usuario_h")
	private String usuarioH;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "Fec_creacion")
	private Date fecCreacion;

	private Empleado empleado;

	@Column(name = "Mca_habilitado")
	private Boolean mCAHabilitado;

	public CuentaH() {
	}

	public CuentaH(Cuenta c, String user) {
		numeroCuenta = c.getNumeroCuenta();
		movimientos = c.getMovimientos();
		fecActu = c.getFecActu();
		fecCreacion = c.getFecCreacion();
		mCAHabilitado = c.getMCAHabilitado();
		usuario = c.getUsuario();
		fecAudit = new Date();
		this.usuarioH = user;
	}

	public CuentaH(String numeroCuenta, Set<Movimiento> movimientos, Date fecActu, Date fecCreacion,
			Boolean mCA_Habilitado, String usuario) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.fecActu = fecActu;
		fecAudit = new Date();
		this.fecCreacion = fecCreacion;
		this.mCAHabilitado = mCA_Habilitado;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<Movimiento> getMovimientos() {
		return new HashSet<>(movimientos);
	}

	protected void setMovimientos(Set<Movimiento> movimientos) {
		this.movimientos = movimientos;
	}

	public Empleado getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	// Getters y Setters de Auditoria

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecActu() {
		return fecActu;
	}

	public void setFecActu(Date fecActu) {
		this.fecActu = fecActu;
	}

	public Boolean getMCAHabilitado() {
		return mCAHabilitado;
	}

	public void setMCAHabilitado(Boolean mCAHabilitado) {
		this.mCAHabilitado = mCAHabilitado;
	}

	public Date getFecCreacion() {
		return fecCreacion;
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
		result = prime * result + ((fecActu == null) ? 0 : fecActu.hashCode());
		result = prime * result + ((numeroCuenta == null) ? 0 : numeroCuenta.hashCode());
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
		CuentaH other = (CuentaH) obj;
		if (fecActu == null) {
			if (other.fecActu != null)
				return false;
		} else if (!fecActu.equals(other.fecActu))
			return false;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

}
