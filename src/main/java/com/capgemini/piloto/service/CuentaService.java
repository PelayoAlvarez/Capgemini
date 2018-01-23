package com.capgemini.piloto.service;

import java.util.List;

import com.capgemini.piloto.model.Cuenta;

public interface CuentaService {

	public void addCuenta(Cuenta c);
	public void updateCuenta(Cuenta c);
	public List<Cuenta> listCuenta();
	public Cuenta getCuentaId(int id);
	public void removeCuenta(int id);
}
