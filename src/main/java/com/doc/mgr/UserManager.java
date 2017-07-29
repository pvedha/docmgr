package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.DocUser;
import com.doc.dao.ActionsDaoImpl;
import com.doc.dao.OracleDaoImpl;
import com.doc.dao.UserDaoImpl;
import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;

public class UserManager {

	public static final UserDaoImpl dao = new UserDaoImpl();
	
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
				
		DocUser user = dao.getUser(userId);
		AuthenticationDto response = new AuthenticationDto(user, getMyOpenActionCount(userId));
		response.setToken(token); //we can regenerate with time	// TODO we need this? doing it again?	
		return response;
	}
	
	
	public int updateMyProfile(AuthenticationDto dto){
		return dao.updateMyProfile(dto);
	}
	
	public int resetPassword(String userId){
		return dao.resetPassword(userId);
	}
	
	public Boolean validateToken(String userId, String token){
		if(new AuthenticationDto().genToken(userId).equals(token)){
			return true;
		}
		return false;
	}
	
	private AuthenticationDto makeAuthDto(DocUser user) {
		return (new AuthenticationDto(user, getMyOpenActionCount(user.getUserid())));
	}		
	
	private int getMyOpenActionCount(String userId){
		ActionsDaoImpl actionDao = new ActionsDaoImpl();
		return actionDao.getMyOpenActionCount(userId);
	}
}
