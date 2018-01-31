package com.capgemini.piloto.model.historico;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.Empleado;
import com.capgemini.piloto.model.Sucursal;
import com.capgemini.piloto.model.Transferencia;
import com.capgemini.piloto.model.dto.EmpleadoDTO;

@Entity
@Table(name = "Empleado_H")
public class EmpleadoH implements Serializable {

	private static final long serialVersionUID = -4428784972707162023L;

	@NotBlank
	@Column(name = "Dni")
	private String dni;

	@NotBlank
	@Column(name = "Nombre")
	private String nombre;

	@NotBlank
	@Column(name = "Apellidos")
	private String apellidos;

	@NotBlank
	@Column(name = "Direccion")
	private String direccion;

	@Column(name = "Fijo")
	private String fijo;

	@Column(name = "Movil")
	private String movil;

	@Column(name = "Email")
	private String email;

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

	@OneToMany(mappedBy = "empleado")
	private Set<Transferencia> transferencias = new HashSet<>();

	@ManyToOne
	@JoinColumn(name = "id_sucursal", nullable = false)
	private Sucursal sucursal;

	@Id
	@Column(name = "Fec_audit", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecAudit;

	@NotBlank
	@Column(name = "Usuario_h")
	private String usuarioH;

	public EmpleadoH() {
	}

	public EmpleadoH(Empleado empleado, String usuarioH) {
		super();
		this.dni = empleado.getDni();
		this.nombre = empleado.getNombre();
		this.apellidos = empleado.getApellidos();
		this.direccion = empleado.getDireccion();
		this.fijo = empleado.getFijo();
		this.movil = empleado.getMovil();
		this.email = empleado.getEmail();
		this.fecActu = empleado.getFecActu();
		this.fecCreacion = empleado.getFecCreacion();
		this.usuario = empleado.getUsuario();
		this.transferencias = empleado.getTransferencias();
		this.sucursal = empleado.getSucursal();
		this.fecAudit = new Date();
		this.usuarioH = usuarioH;
	}

	public EmpleadoH(Empleado empleado, EmpleadoDTO empleadoDto, String usuarioH) {
		super();
		this.dni = empleado.getDni();
		this.nombre = empleadoDto.getNombre();
		this.apellidos = empleadoDto.getApellidos();
		this.direccion = empleadoDto.getDireccion();
		this.fijo = empleadoDto.getFijo();
		this.movil = empleadoDto.getMovil();
		this.email = empleadoDto.getEmail();
		this.fecActu = empleado.getFecActu();
		this.fecCreacion = empleado.getFecCreacion();
		this.usuario = empleado.getUsuario();
		this.transferencias = empleado.getTransferencias();
		this.sucursal = empleado.getSucursal();
		this.fecAudit = new Date();
		this.usuarioH = usuarioH;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFijo() {
		return fijo;
	}

	public void setFijo(String fijo) {
		this.fijo = fijo;
	}

	public String getMovil() {
		return movil;
	}

	public void setMovil(String movil) {
		this.movil = movil;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public Set<Transferencia> getTransferencias() {
		return new HashSet<>(transferencias);
	}

	protected Set<Transferencia> _getTransferencias() {
		return transferencias;
	}

	protected void setTransferencias(Set<Transferencia> transferencias) {
		this.transferencias = transferencias;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
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
		result = prime * result + ((dni == null) ? 0 : dni.hashCode());
		result = prime * result + ((fecAudit == null) ? 0 : fecAudit.hashCode());
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
		EmpleadoH other = (EmpleadoH) obj;
		if (dni == null) {
			if (other.dni != null)
				return false;
		} else if (!dni.equals(other.dni))
			return false;
		if (fecAudit == null) {
			if (other.fecAudit != null)
				return false;
		} else if (!fecAudit.equals(other.fecAudit))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "EmpleadoH [dni=" + dni + ", nombre=" + nombre + ", apellidos=" + apellidos + ", direccion=" + direccion
				+ ", fijo=" + fijo + ", movil=" + movil + ", email=" + email + ", fecActu=" + fecActu + ", fecCreacion="
				+ fecCreacion + ", usuario=" + usuario + ", mcaHabilitado=" + mcaHabilitado + ", transferencias="
				+ transferencias + ", sucursal=" + sucursal + ", fecAudit=" + fecAudit + ", usuarioH=" + usuarioH + "]";
	}
}
