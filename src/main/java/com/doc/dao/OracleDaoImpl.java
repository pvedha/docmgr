package com.doc.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.doc.logger.Logger;
import com.doc.api.DocUser;

public class OracleDaoImpl implements DAO {
	
	//private static final OracleDaoImpl instance = new OracleDaoImpl();
	
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("doc");
	
//	public static OracleDaoImpl getInstance(){
//		return instance;
//	}
//	
//	private OracleDaoImpl() {
//	}
//	
	@Override
	public int userCreate(DocUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ArrayList<DocUser> readAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<String> readUserIds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DocUser validateLogin(String userId, String password) {
		EntityManager em = factory.createEntityManager();
		String validateQuery = "select * from docuser where  userid = :userId and password = :password";
		@SuppressWarnings("unchecked")
		ArrayList<DocUser> docUsers = (ArrayList<DocUser>) em.createNativeQuery(validateQuery, DocUser.class)
				.setParameter("userId", userId).setParameter("password", password).getResultList();
		em.close();
		Logger.log(docUsers.toString());
		if (docUsers.size() == 1) {
			return docUsers.get(0);
		} else {
			return null;
		}		
	}

	@Override
	public DocUser getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int initDB() {
		EntityManager em = factory.createEntityManager();
		em.getTransaction().begin();
		String jobQuery = "insert into jobtitle values ('Administrator',4,4,4)";
		em.createNativeQuery(jobQuery).executeUpdate();
		jobQuery = "insert into jobtitle values ('Teacher',4,4,4)";
		em.createNativeQuery(jobQuery).executeUpdate();
		jobQuery = "insert into jobtitle values ('Therapist',4,4,4)";
		em.createNativeQuery(jobQuery).executeUpdate();
		
		String userQuery = "insert into docuser values ('p','p','p','p','Administrator')";
		em.createNativeQuery(userQuery).executeUpdate();
		userQuery = "insert into docuser values ('admin','admin','admin','p','Administrator')";
		em.createNativeQuery(userQuery).executeUpdate();		
					
		em.getTransaction().commit();
		em.close();
		Logger.log("Init success");
		return 0;
	}

	@Override
	public int initTry() {
		EntityManager em = factory.createEntityManager();
//		em.getTransaction().begin();
//		String userQuery = "create table something (one number, two number)";
//		em.createNativeQuery(userQuery).executeUpdate();
//		em.getTransaction().commit();
		em.close();
		Logger.log("Init success");
		return 0;
	}
}
