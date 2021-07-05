package com.demo;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.demo.entities.Address;
import com.demo.entities.Courses;
import com.demo.entities.Fresher;

public class Main {
	public static void main(String[] args) {

//		Courses courses = new Courses();
//		courses.setId(1);
//		courses.setName("ORM");
//
//		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
//		
//		try {
//			Session session = sessionFactory.openSession();
//			
//			session.beginTransaction();
//			
//			session.save(courses);
//			
//			session.getTransaction().commit();
//		} catch (Exception e) {
//			System.out.println(e.toString()); 
//		}

//		createFresherAndAddress();
		
		createFresherAndCourse();

		HibernateUtils.getSessionFactory().close();

	}

	private static void createFresherAndCourse() {

		List<Courses> courses = new ArrayList<>();
		
		courses.add(new Courses("Jav"));
		courses.add(new Courses("Au"));
		courses.add(new Courses("My"));

		Fresher fresher = new Fresher("Tien", courses);

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		try {
			Session session = sessionFactory.openSession();

			session.beginTransaction();
			
			for (Courses courses2 : courses) {
				session.save(courses2);
			}

			session.save(fresher);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	private static void createFresherAndAddress() {

		Address address = new Address("Hai Chau", "Da Nang");

		Fresher fresher = new Fresher("Tien", address);

		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();

		try {
			Session session = sessionFactory.openSession();

			session.beginTransaction();

			session.save(address);

			session.save(fresher);

			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
