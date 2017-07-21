package com.doc.dao;

import java.util.ArrayList;

import com.doc.api.DocUser;

public interface DAO {

	
	//user
	int userCreate(DocUser user);	
	ArrayList<DocUser> readAllUsers();
	ArrayList<String> readUserIds();
	DocUser validateLogin(String userId, String password);
	DocUser getUser(String userId);
	
		
	//generic
	int initDB();
	int initTry();
}
