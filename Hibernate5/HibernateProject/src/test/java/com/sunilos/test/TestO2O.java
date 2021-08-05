package com.sunilos.test;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.sunilos.mapping.Instructor;
import com.sunilos.mapping.InstructorDetail;
import com.sunilos.util.HibernateUtil;

public class TestO2O {

	public static void main(String[] args) throws Exception {

		SessionFactory sf = HibernateUtil.getSessionFactory();
		Session session = sf.openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();

			InstructorDetail instructorDetail = new InstructorDetail();
			instructorDetail.setHobby("Coding");
			
			Instructor instructor = new Instructor();
			instructor.setFirstName("Rays");
			instructor.setLastName("Tech");
			instructor.setInstructorDetail(instructorDetail);

			session.persist(instructor);

			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
		} finally {
			session.close();
			HibernateUtil.shutdown();
		}
	}

}
