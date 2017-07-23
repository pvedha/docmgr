package com.doc.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DaoImpl {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("doc");
	
	protected void persistObject(Object object){
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		em.persist(object);
		em.getTransaction().commit();
		em.close();
	}
}
