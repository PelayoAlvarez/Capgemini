package com.capgemini.piloto.service;

import java.util.List;

import com.capgemini.piloto.model.Transferencia;

public interface TransferenciaService {

	public void addTransferencia(Transferencia t);
	public void updateTransferencia(Transferencia t);
	public List<Transferencia> listTransferencia();
	public Transferencia getTransferenciaId(int id);
	public void removeTransferencia(int id);
}
