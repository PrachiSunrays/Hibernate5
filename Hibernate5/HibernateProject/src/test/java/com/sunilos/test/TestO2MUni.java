package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.dto.User;
import com.sunilos.mapping.Department;
import com.sunilos.mapping.Employee;
import com.sunilos.util.HibernateUtil;

public class TestO2MUni {

	public static void main(String[] args) throws Exception {

		testAdd();
		// testGet();
	}

	private static void testAdd() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			Department department = new Department();
			department.setName("IT Department");

			Employee employee1 = new Employee();
			employee1.setName("Vijay");
			employee1.setDesignation("IT Manager");
			employee1.setDepartment(department);

			Employee employee2 = new Employee();
			employee2.setName("Ajay");
			employee2.setDesignation("Sr. Software Engineer");
			employee2.setDepartment(department);
			
			Employee employee3 = new Employee();
			employee3.setName("Smith");
			employee3.setDesignation("Associate  Engineer");
			employee3.setDepartment(department);

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
