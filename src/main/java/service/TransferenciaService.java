package service;

import java.util.List;

import model.TransferenciaModel;

public interface TransferenciaService {

	public void addTransferencia(TransferenciaModel t);
	public void updateTransferencia(TransferenciaModel t);
	public List<TransferenciaModel> listTransferencia();
	public TransferenciaModel getTransferenciaId(int id);
	public void removeTransferencia(int id);
}
