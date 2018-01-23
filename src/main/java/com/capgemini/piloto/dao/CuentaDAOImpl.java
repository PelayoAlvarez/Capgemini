package com.capgemini.piloto.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.capgemini.piloto.model.Cuenta;

@Repository
public class CuentaDAOImpl implements CuentaDAO {

	private static final Logger logger = LoggerFactory.getLogger(CuentaDAOImpl.class);

	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	@Override
	public void addCuenta(Cuenta c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.persist(c);
		logger.info("Account saved successfully, Account Details="+ c.toString());
		
	}

	@Override
	public void updateCuenta(Cuenta c) {
		Session session = this.sessionFactory.getCurrentSession();
		session.update(c);
		logger.info("Account updated successfully, account Details="+ c.toString());
		
	}

	@Override
	public List<Cuenta> listCuenta() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Cuenta> accountList = session.createQuery("from cuenta").list();
		for(Cuenta c : accountList){
			logger.info("Account List::"+ c.toString());
		}
		return accountList;
	}

	@Override
	public Cuenta getCuentaId(int id) {
		Session session = this.sessionFactory.getCurrentSession();		
		Cuenta c = (Cuenta) session.load(Cuenta.class, new Integer(id));
		logger.info("Account loaded successfully, account details="+c.toString());
		return c;
	}

	@Override
	public void removeCuenta(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Cuenta c = (Cuenta) session.load(Cuenta.class, new Integer(id));
		if(null != c){
			session.delete(c);
		}
		try {
			logger.info("Account deleted successfully, account details="+ c.toString());
		} catch (Exception e) {
			logger.info("Could not show the information regarding the deleted account");
			e.printStackTrace();
		}
		
	}

}
