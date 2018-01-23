package service;

import java.util.List;

import javax.transaction.Transactional;

import dao.TransferenciaDAO;
import model.TransferenciaModel;

public class TransferenciaServiceImpl implements TransferenciaService {

	private TransferenciaDAO transferDAO;

	public void setTransferDAO(TransferenciaDAO transferDAO) {
		this.transferDAO= transferDAO;
	}
	
	@Override
	@Transactional
	public void addTransferencia(TransferenciaModel t) {
		this.transferDAO.addTransferencia(t);
		
	}

	@Override
	@Transactional
	public void updateTransferencia(TransferenciaModel t) {
		this.transferDAO.updateTransferencia(t);
		
	}

	@Override
	@Transactional
	public List<TransferenciaModel> listTransferencia() {
		return this.transferDAO.listTransferencia();
	}

	@Override
	public TransferenciaModel getTransferenciaId(int id) {
		return this.transferDAO.getTransferenciaId(id);
	}

	@Override
	public void removeTransferencia(int id) {
		this.transferDAO.removeTransferencia(id);
		
	}

}
