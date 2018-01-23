//package com.capgemini.piloto.service.impl;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import com.capgemini.piloto.dao.CuentaDAO;
//import com.capgemini.piloto.model.Cuenta;
//import com.capgemini.piloto.service.CuentaService;
//
//public class CuentaServiceImpl implements CuentaService {
//
//	private CuentaDAO cuentaDAO;
//
//	public void setCuentaDAO(CuentaDAO cuentaDAO) {
//		this.cuentaDAO= cuentaDAO;
//	}
//	
//	@Override
//	@Transactional
//	public void addCuenta(Cuenta c) {
//		this.cuentaDAO.addCuenta(c);
//		
//	}
//
//	@Override
//	@Transactional
//	public void updateCuenta(Cuenta c) {
//		this.cuentaDAO.updateCuenta(c);
//		
//	}
//
//	@Override
//	@Transactional
//	public List<Cuenta> listCuenta() {
//		return this.cuentaDAO.listCuenta();
//	}
//
//	@Override
//	public Cuenta getCuentaId(int id) {
//		return this.cuentaDAO.getCuentaId(id);
//	}
//
//	@Override
//	public void removeCuenta(int id) {
//		this.cuentaDAO.removeCuenta(id);
//		
//	}
//
//}
