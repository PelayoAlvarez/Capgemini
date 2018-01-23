package com.capgemini.piloto.dao;

import java.util.List;

import com.capgemini.piloto.model.Cuenta;

public interface CuentaDAO {

	public void addCuenta(Cuenta c);
	public void updateCuenta(Cuenta c);
	public List<Cuenta> listCuenta();
	public Cuenta getCuentaId(int id);
	public void removeCuenta(int id);
}
