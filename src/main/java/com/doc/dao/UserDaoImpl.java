package com.doc.dao;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;

import com.doc.api.ActionStates;
import com.doc.api.DocState;
import com.doc.api.DocUser;
import com.doc.api.Jobtitle;
import com.doc.api.Properties;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.JobTitleNotValidException;
import com.doc.exceptions.StaffNotFoundException;
import com.doc.utilities.Environment;
import com.doc.utilities.Logger;

public class UserDaoImpl extends DaoImpl {

	public UserDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	public int addStaff(UserDto user) {
		EntityManager em = factory.createEntityManager();
		if (em.find(DocUser.class, user.getUserId()) != null) {
			throw new DuplicateUserException();
		}

		Jobtitle jobtitle = em.find(Jobtitle.class, user.getJobTitle());
		if (jobtitle == null) {
			throw new JobTitleNotValidException(user.getJobTitle());
		}
		// TODO try to standardize the annoying userid UserId userId thingy
		DocUser docUser = new DocUser();
		docUser.setName(user.getUserName());
		docUser.setUserid(user.getUserId());
		docUser.setPassword(user.getUserId());
		docUser.setAbout(user.getAbout());
		docUser.setEmail(user.getEmail());
		docUser.setPhone(user.getPhone());
		docUser.setJobtitle(jobtitle);
		em.getTransaction().begin();
		em.persist(docUser);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<DocUser> readAllUsers() {
		EntityManager em = factory.createEntityManager();
		ArrayList<DocUser> docUsers = (ArrayList<DocUser>) em.createNativeQuery("select * from docuser", DocUser.class)
				.getResultList();
		em.close();
		return docUsers;
	}

	public DocUser validateLogin(String userId, String password) {
		EntityManager em = factory.createEntityManager();
		DocUser user = em.find(DocUser.class, userId);
		if(user != null)
			Logger.log(user.toString());
		em.close();
		
		
		if(user == null){
			if(userId.contentEquals(Environment.ADMIN_NAME)){
				//create new Admin user		
				return createAdmin(userId, password);
			}
			return null;
		}
		if(user.getPassword().equals(password)){
			return user;
		}
		
		return null;
	
	}

	private DocUser createAdmin(String userId, String password){
		
		EntityManager em = factory.createEntityManager();
		
		Jobtitle admin = em.find(Jobtitle.class, Environment.ADMINISTRATOR);
		
		if(admin == null){
			admin = new Jobtitle();
			admin.setTitle(Environment.ADMINISTRATOR);
			admin.setAddChildren(true);
			admin.setAddStaff(true);
			admin.setManageSettings(true);
			admin.setManageUserControls(true);
			admin.setRemarks(Environment.ADMINISTRATOR);
			admin.setViewAllActions(true);
			admin.setViewAllChildren(true);
			admin.setViewAllDocuments(true);
			em.getTransaction().begin();
			em.persist(admin);
			em.getTransaction().commit();
		}
		
		DocUser adminUser = new DocUser();
		
		adminUser.setUserid(Environment.ADMIN_NAME);
		adminUser.setJobtitle(admin);
		adminUser.setAbout(Environment.ADMINISTRATOR);
		adminUser.setName(Environment.ADMINISTRATOR);
		adminUser.setPassword(password);
		em.getTransaction().begin();
		em.persist(adminUser);
		em.getTransaction().commit();
		em.close();
		initSystem();
		return adminUser;	
		
	}	
	
	public DocUser getUser(String userId) {
		EntityManager em = factory.createEntityManager();
		DocUser user = em.find(DocUser.class, userId);
		em.close();
		return user;
	}

	public int updateMyProfile(AuthenticationDto dto) {
		EntityManager em = factory.createEntityManager();
		DocUser user = em.find(DocUser.class, dto.getUserId());
		if (user == null) {
			throw new StaffNotFoundException(dto.getName() + " : " + dto.getUserId());
		}

		user.setAbout(dto.getAbout());
		user.setEmail(dto.getEmail());
		user.setPhone(dto.getPhone());

		if (!dto.getPassword().isEmpty()){
			user.setPassword(dto.getPassword());
		}
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		return 0;
	}

	public int resetPassword(String userId) {
		EntityManager em = factory.createEntityManager();
		DocUser user = em.find(DocUser.class, userId);
		if (user == null) {
			throw new StaffNotFoundException(userId);
		}

		user.setPassword(user.getUserid());

		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();

		return 0;
	}
	
	private void initSystem(){
		
		for(Map.Entry<String,String> item : Environment.actionStates.entrySet()){
			addActionState(item.getKey(), item.getValue());			
		}
		
		for(Map.Entry<String,String> item : Environment.docStates.entrySet()){
			addDocState(item.getKey(), item.getValue());			
		}
		
		for(Map.Entry<String,String> item : Environment.properties.entrySet()){
			addProperties(item.getKey(), item.getValue());			
		}
	}
	
	public void addActionState(String state, String remarks){
		EntityManager em = factory.createEntityManager();
		ActionStates action = new ActionStates(state, remarks);
		em.getTransaction().begin();
		em.persist(action);
		em.getTransaction().commit();
		em.close();		
	}
	
	public void addDocState(String state, String remarks){
		EntityManager em = factory.createEntityManager();
		DocState docState = new DocState(state, remarks);
		em.getTransaction().begin();
		em.persist(docState);
		em.getTransaction().commit();
		em.close();		
	}
	
	public void addProperties(String key, String value){
		EntityManager em = factory.createEntityManager();
		Properties property = new Properties(key, value);
		em.getTransaction().begin();
		em.persist(property);
		em.getTransaction().commit();
		em.close();		
	}
}
