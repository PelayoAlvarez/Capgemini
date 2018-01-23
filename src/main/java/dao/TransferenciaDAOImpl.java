package dao;

import java.util.List;

import org.springframework.stereotype.Repository;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import model.TransferenciaModel;

@Repository
public class TransferenciaDAOImpl implements TransferenciaDAO {

	private static final Logger logger = LoggerFactory.getLogger(TransferenciaDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addTransferencia(TransferenciaModel t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(t);
		logger.info("Transfer saved successfully, Transfer Details="+ t);
		
	}

	@Override
	public void updateTransferencia(TransferenciaModel t) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(t);
		logger.info("Tranfer updated successfully, transfer Details="+t);
		
	}

	@Override
	public List<TransferenciaModel> listTransferencia() {
		Session session = this.sessionFactory.getCurrentSession();
		List<TransferenciaModel> transferList = session.createQuery("from transferencia").list();
		for(TransferenciaModel t : transferList){
			logger.info("Person List::"+t);
		}
		return transferList;
	}

	@Override
	public TransferenciaModel getTransferenciaId(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		TransferenciaModel t = (TransferenciaModel) session.load(TransferenciaModel.class, new Integer(id));
		logger.info("Person loaded successfully, Person details="+t);
		return t;
	}

	@Override
	public void removeTransferencia(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		TransferenciaModel t = (TransferenciaModel) session.load(TransferenciaModel.class, new Integer(id));
		if(null != t){
			session.delete(t);
		}
		logger.info("Transfer deleted successfully, transfer details="+t);
		
	}

}
