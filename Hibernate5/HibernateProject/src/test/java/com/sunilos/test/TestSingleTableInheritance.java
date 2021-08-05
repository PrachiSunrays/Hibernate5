package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.inheritanc.Account;
import com.sunilos.inheritanc.Credit;
import com.sunilos.inheritanc.Debit;
import com.sunilos.util.HibernateUtil;

public class TestSingleTableInheritance {

	public static void main(String[] args) throws Exception {

		Credit c = new Credit();
		c.setBalance(2000);
		c.setCreditLimit(20000);

		Debit d = new Debit();
		d.setBalance(3000);
		d.setOverdraftFee(300);

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			session.save(c);
			session.save(d);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}

}
