package dao;

import java.util.List;

import model.TransferenciaModel;

public interface TransferenciaDAO {

	public void addTransferencia(TransferenciaModel t);
	public void updateTransferencia(TransferenciaModel t);
	public List<TransferenciaModel> listTransferencia();
	public TransferenciaModel getTransferenciaId(int id);
	public void removeTransferencia(int id);
}
