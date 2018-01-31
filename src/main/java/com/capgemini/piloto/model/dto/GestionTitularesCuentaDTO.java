package com.capgemini.piloto.model.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.capgemini.piloto.model.ClienteCuenta;

public class GestionTitularesCuentaDTO {
	private String numeroCuenta;
	private Set<String> dniTitulares = new HashSet<>();

	public String getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(String numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public Set<String> getDniTitulares() {
		return dniTitulares;
	}

	public void setDniTitulares(Set<String> dniTitulares) {
		this.dniTitulares = dniTitulares;
	}

	public GestionTitularesCuentaDTO() {

	}

	public GestionTitularesCuentaDTO(String numeroCuenta, Set<String> dniTitulares) {
		this.numeroCuenta = numeroCuenta;
		this.dniTitulares = dniTitulares;
	}

	public GestionTitularesCuentaDTO(ClienteCuenta clienteCuenta) {
		this.numeroCuenta = clienteCuenta.getCuenta().getNumeroCuenta();
		this.dniTitulares.add(clienteCuenta.getCliente().getDni());
	}

	public GestionTitularesCuentaDTO(List<ClienteCuenta> ccs) {
		ccs.forEach(cc -> dniTitulares.add(cc.getCliente().getDni()));
	}
}
