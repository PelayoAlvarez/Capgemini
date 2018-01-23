//package com.capgemini.piloto.service.impl;
//
//import java.util.List;
//
//import javax.transaction.Transactional;
//
//import com.capgemini.piloto.dao.TransferenciaDAO;
//import com.capgemini.piloto.model.Transferencia;
//import com.capgemini.piloto.service.TransferenciaService;
//
//public class TransferenciaServiceImpl implements TransferenciaService {
//
//	private TransferenciaDAO transferDAO;
//
//	public void setTransferDAO(TransferenciaDAO transferDAO) {
//		this.transferDAO= transferDAO;
//	}
//	
//	@Override
//	@Transactional
//	public void addTransferencia(Transferencia t) {
//		this.transferDAO.addTransferencia(t);
//		
//	}
//
//	@Override
//	@Transactional
//	public void updateTransferencia(Transferencia t) {
//		this.transferDAO.updateTransferencia(t);
//		
//	}
//
//	@Override
//	@Transactional
//	public List<Transferencia> listTransferencia() {
//		return this.transferDAO.listTransferencia();
//	}
//
//	@Override
//	public Transferencia getTransferenciaId(int id) {
//		return this.transferDAO.getTransferenciaId(id);
//	}
//
//	@Override
//	public void removeTransferencia(int id) {
//		this.transferDAO.removeTransferencia(id);
//		
//	}
//
//}
