package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.inheritanc.Cheque;
import com.sunilos.inheritanc.CreditCard;
import com.sunilos.util.HibernateUtil;

public class TestInheritance {
	
	public static void main(String[] args) throws Exception {
		
		CreditCard cc = new CreditCard();
		cc.setAmount(1000);
		cc.setCcTye("VISA");
		
		Cheque cheque = new Cheque();
		cheque.setAmount(56000);
		cheque.setCheqNo(45612);
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
		
			session.save(cc);
			session.save(cheque);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}

}
