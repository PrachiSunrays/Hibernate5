package com.sunilos.mapping;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.dto.HibernateUtil;
import com.sunilos.dto.User;

public class TestO2M {

	public static void main(String[] args) throws Exception {
		
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Employee employee1 = new Employee();
	         employee1.setName("Vijay");
	         employee1.setDesignation("IT Manager");

	         Employee employee2 = new Employee();
	         employee2.setName("Ajay");
	         employee2.setDesignation("Sr. Software Engineer");

	         Employee employee3 = new Employee();
	         employee3.setName("Smith");
	         employee3.setDesignation("Associate  Engineer");

	         Department department = new Department();
	         department.setName("IT Department");
	         department.getEmployees().add(employee1);
	         department.getEmployees().add(employee2);
	         department.getEmployees().add(employee3);

	         session.persist(department);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
}
