package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.DocUser;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;

public class UserManager extends DocManager {

	public UserManager() {		
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
