package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.mapping.Department;
import com.sunilos.mapping.Employee;
import com.sunilos.mapping.State;
import com.sunilos.util.HibernateUtil;

public class TestBiOne2Many {
	
public static void main(String[] args) throws Exception {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			State state = new State();
			state.setStateName("MP");
			
	         session.persist(state);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}

}
