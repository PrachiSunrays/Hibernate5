package com.sunilos.dto;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class TestNamedQuery {

	public static void main(String[] args) throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		
		Query query = session.getNamedQuery("allUser");
		
		List<User> list = query.getResultList();
		for (User user : list) {
			System.out.println(user.getId());
			System.out.println(user.getFirstName());
		}
	}
}
