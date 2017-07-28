package com.doc.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import com.doc.api.Children;
import com.doc.api.DocUser;
import com.doc.api.Jobtitle;
import com.doc.api.Properties;
import com.doc.dto.ChildrenDto;
import com.doc.dto.UserDto;
import com.doc.exceptions.ChildNotFoundException;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.JobTitleNotValidException;
import com.doc.exceptions.NotAuthorizedException;
import com.doc.exceptions.StaffNotFoundException;
import com.doc.utilities.Logger;
import com.doc.utilities.QueryStatements;
import com.doc.utilities.Utilities;

public class OracleDaoImpl extends DaoImpl implements DAO {

	// private static final OracleDaoImpl instance = new OracleDaoImpl();

	// public static OracleDaoImpl getInstance(){
	// return instance;
	// }
	//
	// private OracleDaoImpl() {
	// }
	//

	public OracleDaoImpl() {
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

	public int addChild(ChildrenDto dto) {
		EntityManager em = factory.createEntityManager();
		if (em.find(Children.class, dto.getId()) != null) {
			throw new DuplicateUserException("Id " + dto.getId() + " exists");
		}
		;
		DocUser teacher = em.find(DocUser.class, dto.getTeacher());
		if (teacher == null) {
			throw new StaffNotFoundException("Teacher : " + dto.getTeacher());
		}
		DocUser councillor = em.find(DocUser.class, dto.getCouncillor());
		if (councillor == null) {
			throw new StaffNotFoundException("Councillor : " + dto.getCouncillor());
		}
		DocUser therapist = em.find(DocUser.class, dto.getTherapist());
		if (therapist == null) {
			throw new StaffNotFoundException("Therapist : " + dto.getTherapist());
		}
		Children child = new Children();
		child.setId(dto.getId());
		child.setName(dto.getName());
		child.setDob(Utilities.getTimeStamp(dto.getDob()));
		child.setDoj(Utilities.getTimeStamp(dto.getDoj()));
		child.setRemarks(dto.getRemarks());
		child.setMessage(dto.getMessage());
		child.setTeacher(teacher);
		child.setCouncillor(councillor);
		child.setTherapist(therapist);
		child.setTags(dto.getTags());
		em.getTransaction().begin();
		em.persist(child);
		em.getTransaction().commit();
		em.close();
		return 0;
	}

	public int updateChild(ChildrenDto dto) {
		EntityManager em = factory.createEntityManager();

		Children child = em.find(Children.class, dto.getId());
		if (child == null) {
			throw new ChildNotFoundException(dto.getId() + " : " + dto.getName());
		}
		DocUser teacher = em.find(DocUser.class, dto.getTeacher());
		if (teacher == null) {
			throw new StaffNotFoundException("Teacher : " + dto.getTeacher());
		}
		DocUser councillor = em.find(DocUser.class, dto.getCouncillor());
		if (councillor == null) {
			throw new StaffNotFoundException("Councillor : " + dto.getCouncillor());
		}
		DocUser therapist = em.find(DocUser.class, dto.getTherapist());
		if (therapist == null) {
			throw new StaffNotFoundException("Therapist : " + dto.getTherapist());
		}

		child.setRemarks(dto.getRemarks());
		child.setMessage(dto.getMessage());
		child.setTeacher(teacher);
		child.setCouncillor(councillor);
		child.setTherapist(therapist);
		child.setTags(dto.getTags());
		em.getTransaction().begin();
		em.persist(child);
		em.getTransaction().commit();
		em.close();

		return 0;
	}

	@Override
	public int userCreate(DocUser user) {
		// TODO Auto-generated method stub
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DocUser> readAllUsers() {
		EntityManager em = factory.createEntityManager();
		ArrayList<DocUser> docUsers = (ArrayList<DocUser>) em.createNativeQuery("select * from docuser", DocUser.class)
				.getResultList();
		em.close();
		return docUsers;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Children> readAllChildren() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Children> childrens = (ArrayList<Children>) em
				.createNativeQuery("select * from children", Children.class).getResultList();
		em.close();
		return childrens;
	}

	public Children findChildById(Integer id) {
		EntityManager em = factory.createEntityManager();
		Children child = em.find(Children.class, id);
		if (child == null) {
			throw new ChildNotFoundException(id.toString());
		}
		return child;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Children> searchChildren(ArrayList<String> keys) {

		EntityManager em = factory.createEntityManager();
		String searchQuery = "select * from children where ";

		for (String key : keys) {
			searchQuery += Utilities.getTextSearchQuery("name", key) + " or "
					+ Utilities.getTextSearchQuery("remarks", key) + " or "
					+ Utilities.getTextSearchQuery("message", key) + " or " 
					+ Utilities.getTextSearchQuery("tags", key);
			if (keys.indexOf(key) < keys.size() - 1) {
				searchQuery += " or ";
			}
		}
		
		Logger.log("The query is " + searchQuery);

		ArrayList<Children> childrens = (ArrayList<Children>) em.createNativeQuery(searchQuery, Children.class)
				.getResultList();
		em.close();
		return childrens;
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
		if (docUsers.size() == 1) {
			return docUsers.get(0);
		} else {
			return null;
		}
	}

	@Override
	public DocUser getUser(String userId) {
		EntityManager em = factory.createEntityManager();
		DocUser user = em.find(DocUser.class, userId);
		em.close();
		return user;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Properties> getProperties() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Properties> properties = (ArrayList<Properties>) em
				.createNativeQuery("select * from properties", Properties.class).getResultList();
		em.close();
		return properties;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Jobtitle> getJobTitles() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Jobtitle> jobTitles = (ArrayList<Jobtitle>) em
				.createNativeQuery("select * from jobtitle", Jobtitle.class).getResultList();
		em.close();
		return jobTitles;
	}

	public int updateJobTitle(Jobtitle dto) {
		EntityManager em = factory.createEntityManager();

		if (dto.getTitle().contentEquals(QueryStatements.administrator)) {
			throw new NotAuthorizedException();
		}

		Jobtitle jt = em.find(Jobtitle.class, dto.getTitle());
		if (jt == null) {
			throw new JobTitleNotValidException(dto.getTitle());
		}

		em.getTransaction().begin();
		em.persist(dto);
		em.getTransaction().commit();
		em.close();
		return 0;
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

		String userQuery = "insert into docuser values ('p','p','p','p','Administrator','praveen.vedha@gmail.com', '0123456789')";
		em.createNativeQuery(userQuery).executeUpdate();
		userQuery = "insert into docuser values ('admin','admin','admin','p','Administrator','admin@gmail.com', '9876543210')";
		em.createNativeQuery(userQuery).executeUpdate();

		String childQuery = "insert into CHILDREN values (1, sysdate, sysdate, 'message', 'cname', 'remarks', 'tag1, tag2', 'p', 'p', 'p')";
		em.createNativeQuery(childQuery).executeUpdate();

		em.getTransaction().commit();
		em.close();
		Logger.log("Init success");
		return 0;
	}

	@Override
	public int initTry() {
		EntityManager em = factory.createEntityManager();
		// em.getTransaction().begin();
		// String userQuery = "create table something (one number, two number)";
		// em.createNativeQuery(userQuery).executeUpdate();
		// em.getTransaction().commit();
		em.close();
		Logger.log("Init success");
		return 0;
	}

}
