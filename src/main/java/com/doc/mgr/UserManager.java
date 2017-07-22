package com.doc.mgr;

import com.doc.dao.DAO;
import com.doc.dao.OracleDaoImpl;
import com.doc.logger.Logger;

import java.util.ArrayList;

import com.doc.api.DocUser;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;

public class UserManager {

	private OracleDaoImpl dao = new OracleDaoImpl(); //OracleDaoImpl.getInstance(); // = new OracleDAOImpl();
	// TODO see if we can use the interface and init inside the constructor. or singleton

	public UserManager() {
		try {
			Logger.log("Initializing for OracleDAO");
			// dao = new OracleDaoImpl();
		} catch (Exception e) {
			Logger.log("No class found most likely " + e.getMessage());
		}
	}

	public void createUser() {

	};
	
	public  ArrayList<UserDto> readAllUsers(){
		ArrayList<DocUser> docUsers = dao.readAllUsers();
		return getUserDtos(docUsers);
	}

	private ArrayList<UserDto> getUserDtos(ArrayList<DocUser> docUsers){
		ArrayList<UserDto> userDtos = new ArrayList<>();
		for(DocUser docUser: docUsers){
			userDtos.add(new UserDto(docUser));
		}
		return userDtos;
	}
	public AuthenticationDto validateLogin(String userId, String password) {
		DocUser user = dao.validateLogin(userId, password);
		AuthenticationDto token = null;
		if (user != null)
			token = this.makeAuthDto(user);
		return token;
	}

	public AuthenticationDto validateSession(String userId, String token) {		
		if(!validateToken(userId, token)){
			return null;
		}
		Logger.log("proceeding to get the user details");
		AuthenticationDto response = new AuthenticationDto();		
		DocUser user = dao.getUser(userId);
		response.setToken(token); //we can regenerate with time
		response.setAbout(user.getAbout());
		response.setName(user.getName());
		response.setUserId(user.getUserid());	
		response.setJobTitle(user.getJobTitleString());
		return response;
	}
	
	public Boolean validateToken(String userId, String token){
		if(new AuthenticationDto().genToken(userId).equals(token)){
			return true;
		}
		return false;
	}
	
	private AuthenticationDto makeAuthDto(DocUser user) {
		return (new AuthenticationDto(user.getUserid(), user.getName(), user.getAbout(), user.getJobtitle().getTitle()));
	}
	
	
}
