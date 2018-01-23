package com.capgemini.piloto.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.capgemini.piloto.model.Transferencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Repository
public class TransferenciaDAOImpl implements TransferenciaDAO {

	private static final Logger logger = LoggerFactory.getLogger(TransferenciaDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addTransferencia(Transferencia t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(t);
		logger.info("Transfer saved successfully, Transfer Details="+ t);
		
	}

	@Override
	public void updateTransferencia(Transferencia t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		logger.info("Tranfer updated successfully, transfer Details="+t);
		
	}

	@Override
	public List<Transferencia> listTransferencia() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Transferencia> transferList = session.createQuery("from transferencia").list();
		for(Transferencia t : transferList){
			logger.info("Person List::"+t);
		}
		return transferList;
	}

	@Override
	public Transferencia getTransferenciaId(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Transferencia t = (Transferencia) session.load(Transferencia.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+t);
		return t;
	}

	@Override
	public void removeTransferencia(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Transferencia t = (Transferencia) session.load(Transferencia.class, new Integer(id));
		if(null != t){
			session.delete(t);
		}
		logger.info("Transfer deleted successfully, transfer details="+t);
		
	}

}
