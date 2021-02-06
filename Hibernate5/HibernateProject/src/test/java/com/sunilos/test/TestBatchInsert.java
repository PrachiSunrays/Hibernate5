package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.dto.User;
import com.sunilos.util.HibernateUtil;

public class TestBatchInsert {

	public static void main(String[] args) throws Exception {
		//testInsert();
		testUpdate();
	}

	public static void testInsert() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		int batchSize = 50;
		try {
			tx = session.beginTransaction();

			for (int i = 0; i < 200; i++) {
				User user = new User();
				// user.setId(1);
				user.setFirstName("Jay");
				user.setLastName("Verma");
				user.setUserName("jay.verma@gmail.com");
				user.setPassword("123456");
				session.save(user);

				if (i > 0 && i % batchSize == 0) {
					session.flush();
					session.clear();
				}

			}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
		}

		HibernateUtil.shutdown();
	}
	
	public static void testUpdate() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		ScrollableResults results = null;
		int batchSize = 50;
		try {
			tx = session.beginTransaction();

			results = session.createQuery("from User").scroll(ScrollMode.FORWARD_ONLY);
			
				int i = 1;
				while (results.next()) {
					User user = (User)results.get(0);
					user.setUserName("jy@gmail.com");
				}
				if (i > 0 && i % batchSize == 0) {
					session.flush();
					session.clear();
				}
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			//results.close();
			session.close();
		}

		HibernateUtil.shutdown();
	}

}
