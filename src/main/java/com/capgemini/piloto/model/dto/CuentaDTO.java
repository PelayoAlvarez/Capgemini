package com.capgemini.piloto.model.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.hibernate.validator.constraints.NotBlank;

import com.capgemini.piloto.model.ClienteCuenta;
import com.capgemini.piloto.model.Cuenta;
import com.capgemini.piloto.model.Movimiento;
import com.capgemini.piloto.model.Transferencia;

public class CuentaDTO implements Serializable {

	private static final long serialVersionUID = -7283533209815501984L;

	@NotBlank
	private String numeroCuenta;

	private Set<ClienteBDTO> clientes = new HashSet<>();

	private String usuario;

	private Double importe;

	private Set<Long> movimientos = new HashSet<>();

	private Set<Long> transferencias = new HashSet<>();

	// Campos de Auditoria

	@Override
	public String toString() {
		return "Cuenta [numeroCuenta=" + numeroCuenta;
	}

	private Date fecActu;

	private Date fecCreacion;

	private Boolean mCAHabilitado;

	public CuentaDTO() {
		fecCreacion = new Date();
		fecActu = new Date();
	}
	
	public CuentaDTO(double importe)
	{
		this.importe=importe;
		this.usuario="test";
		this.fecActu=new Date();
		this.fecCreacion=new Date();
		this.mCAHabilitado=true;
		this.numeroCuenta=generarNumero();
	}
	public CuentaDTO(Cuenta cuenta) {
		this.numeroCuenta = cuenta.getNumeroCuenta();
		this.usuario = cuenta.getUsuario();
		this.fecActu = new Date();
		this.fecCreacion = new Date();
		for (Transferencia t : cuenta.getTransferencias())
			this.getTransferencias().add(t.getId());
		for (Movimiento m : cuenta.getMovimientos())
			this.getMovimientos().add(m.getId());
		for (ClienteCuenta cl : cuenta.getClienteCuenta())
			this.getClientes().add(new ClienteBDTO(cl.getCliente()));
	}

	public CuentaDTO(String numeroCuenta, Set<Long> movimientos, Set<Long> transferencias, Set<ClienteBDTO> clientes,
			Boolean mCAHabilitado, String usuario) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.movimientos = movimientos;
		this.transferencias = transferencias;
		this.clientes = clientes;
		this.fecActu = new Date();
		this.fecCreacion = new Date();
		this.mCAHabilitado = mCAHabilitado;
		this.usuario = usuario;
	}
	
	public String generarNumero() {
		Random rng = new Random();
		return (rng.nextInt(900000000)+100000000)+""
				+(rng.nextInt(900000000)+100000000)+
				(rng.nextInt(9000000)+1000000);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		CuentaDTO other = (CuentaDTO) obj;
		if (numeroCuenta == null) {
			if (other.numeroCuenta != null)
				return false;
		} else if (!numeroCuenta.equals(other.numeroCuenta))
			return false;
		return true;
	}

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<Long> pgetMovimientos() {
		return new HashSet<>(movimientos);
	}

	public Set<Long> getMovimientos() {
		return movimientos;
	}

	protected void setMovimientos(Set<Long> movimientos) {
		this.movimientos = movimientos;
	}

	public Set<Long> getTransferencias() {
		return transferencias;
	}

	public Set<Long> pgetTransferencias() {
		return new HashSet<>(transferencias);
	}

	public void setTransferencias(Set<Long> transferencias) {
		this.transferencias = transferencias;
	}

	public Set<ClienteBDTO> getClientes() {
		return clientes;
	}

	public Set<ClienteBDTO> pgetClientes() {
		return new HashSet<>(clientes);
	}

	public void setClienteCuenta(Set<ClienteBDTO> clientecuenta) {
		this.clientes = clientecuenta;
	}

	// Getters y Setters de Auditoria

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

	public Double getImporte() {
		return importe;
	}

	public void setImporte(Double importe) {
		this.importe = importe;
	}

}
