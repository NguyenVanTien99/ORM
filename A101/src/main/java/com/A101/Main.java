
package com.A101;

import org.hibernate.SessionFactory;

public class Main {
	
	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
	}

}
