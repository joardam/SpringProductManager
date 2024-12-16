package com.app.connection;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionFactory {
	private static SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	
	public Session getConnection() {
		return sessionFactory.openSession();
	}
}
