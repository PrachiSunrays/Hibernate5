package com.sunilos.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.type.IntegerType;
import org.hibernate.type.StringType;

import com.sunilos.dto.User;
import com.sunilos.util.HibernateUtil;

public class TestNativeQuery {

	public static void main(String[] args) throws Exception {

		testNativeQuery();
		//testNativeQuerySelectedColumn();

	}

	private static void testNativeQuery() throws Exception {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<User> user = session.createNativeQuery("Select * from User", User.class).getResultList();

		for (User u : user) {
			System.out.println(u.getId());
			System.out.println(u.getFirstName());
			System.out.println(u.getLastName());
			System.out.println(u.getUserName());
			System.out.println(u.getPassword());
			
		}

		session.close();
		HibernateUtil.shutdown();
	}

	public static void testNativeQuerySelectedColumn() throws Exception {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();

		List<Object[]> user = session.createNativeQuery("Select id,FIRST_NAME,LAST_NAME from User")
				.addScalar("ID", IntegerType.INSTANCE).addScalar("FIRST_NAME", StringType.INSTANCE)
				.addScalar("LAST_NAME", StringType.INSTANCE).getResultList();

		for (Object[] u : user) {
			Integer id = (Integer) u[0];
			String firstName = (String) u[1];
			String lastName = (String) u[2];
			System.out.println(id + firstName + lastName);
		}

		session.close();
		HibernateUtil.shutdown();
	}

}
