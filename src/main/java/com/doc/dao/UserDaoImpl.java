package com.doc.dao;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.doc.api.DocUser;
import com.doc.api.Jobtitle;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.JobTitleNotValidException;
import com.doc.exceptions.StaffNotFoundException;

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
		String validateQuery = "select * from docuser where  userid = :userId and password = :password";
		@SuppressWarnings("unchecked")
		ArrayList<DocUser> docUsers = (ArrayList<DocUser>) em.createNativeQuery(validateQuery, DocUser.class)
				.setParameter("userId", userId).setParameter("password", password).getResultList();
		em.close();
		if (docUsers.size() == 1) {
			return docUsers.get(0);
		} else {
			return null;
		}
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
}
