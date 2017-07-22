package com.doc.mgr;

import com.doc.dao.DAO;
import com.doc.dao.OracleDaoImpl;
import com.doc.logger.Logger;

import java.util.ArrayList;

import com.doc.api.Children;
import com.doc.api.DocUser;
import com.doc.api.Jobtitle;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.ChildrenDto;
import com.doc.dto.UserDto;

public class UserManager extends DocManager {

	//private OracleDaoImpl dao = new OracleDaoImpl();//OracleDaoImpl.getInstance(); // = new OracleDAOImpl();
	// TODO see if we can use the interface and init inside the constructor. or singleton

	public UserManager() {
		try {
			Logger.log("Initializing for OracleDAOx");
			// dao = new OracleDaoImpl();
		} catch (Exception e) {
			Logger.log("No class found most likely " + e.getMessage());
		}
	}

	public int addStaff(UserDto user) {
		return dao.addStaff(user);
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
	
	public  ArrayList<ChildrenDto> readAllChildren(){
		ArrayList<Children> childrens = dao.readAllChildren();
		return getChildrenDtos(childrens);
	}
	private ArrayList<ChildrenDto> getChildrenDtos(ArrayList<Children> childrens){
		ArrayList<ChildrenDto> childrenDtos = new ArrayList<>();
		for(Children children: childrens){
			childrenDtos.add(new ChildrenDto(children));
		}
		return childrenDtos;
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
