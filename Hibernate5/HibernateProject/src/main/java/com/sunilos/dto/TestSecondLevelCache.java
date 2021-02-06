package com.sunilos.dto;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestSecondLevelCache {

	public static void main(String[] args) throws Exception {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		User u =(User) session.get(User.class, 1);
		System.out.println(u.getId());
		System.out.println(u.getFirstName());
		session.clear();
		
		User u1 =(User) session.get(User.class, 1);
		System.out.println(u1.getId());
		System.out.println(u1.getFirstName());
		session.close();
	}
}
