package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.dto.Employee;
import com.sunilos.dto.EmployeeId;
import com.sunilos.dto.User;
import com.sunilos.util.HibernateUtil;

public class TestCRUD {

	public static void main(String[] args) throws Exception {
		testAdd();
		//testUpdate();
		//testDelete();
		//testGet();
		//testUpdateSingleCloumn();
		//testEmployeeAdd();
	}
	/*
	 * Test Composite key
	 * 
	 */
	public static void testEmployeeAdd() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			
			EmployeeId eid = new EmployeeId();
			eid.setCompanyId("Rays");
			eid.setEmployeeId("123");

			Employee e = new Employee();
			e.setEmployeeId(eid);
			e.setName("Rays");
			e.setEmail("rays@raytech.com");
			e.setPhoneNumber("12345678");
			session.save(e);
			
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
	public static void testAdd() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User();
			//user.setId(1);
			user.setFirstName("Jay");
			user.setLastName("Verma");
			user.setUserName("jay.verma@gmail.com");
			user.setPassword("123456");
			session.save(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
	
	public static void testUpdate() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User();
			user.setId(1);
			user.setFirstName("Jay");
			user.setLastName("Chouhan");
			user.setUserName("jay.chouhan@gmail.com");
			user.setPassword("jay456");
			session.update(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
	public static void testDelete() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			User user = new User();
			user.setId(1);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
	
	public static void testGet() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		User u =(User) session.get(User.class, 1);
		System.out.println(u.getId());
		System.out.println(u.getFirstName());
		System.out.println(u.getLastName());
		System.out.println(u.getUserName());
		System.out.println(u.getPassword());
		session.close();
		HibernateUtil.shutdown();
	}
	public static void testUpdateSingleCloumn() throws Exception {
		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			int id = 1;
			String newName = "Ramesh";
			tx = session.beginTransaction();
			String hqlUpdate = "update User u set u.firstName = :newName where u.id = :id"; 
			// or String hqlUpdate = "update Customer set name = :newName where name = :oldName"; 
			int updatedEntities = session.createQuery( hqlUpdate )
					.setParameter( "newName", newName )
					.setParameter("id", id ).executeUpdate(); 
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}
}
