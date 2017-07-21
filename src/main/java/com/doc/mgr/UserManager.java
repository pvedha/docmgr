package com.doc.mgr;

import com.doc.dao.DAO;
import com.doc.dao.OracleDaoImpl;
import com.doc.logger.Logger;
import com.doc.api.DocUser;
import com.doc.dto.AuthenticationDto;

public class UserManager {

	private OracleDaoImpl dao = new OracleDaoImpl(); //OracleDaoImpl.getInstance(); // = new OracleDAOImpl();
	// TODO see if we can use the interface and init inside the constructor.

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

	public AuthenticationDto validateLogin(String userId, String password) {
		DocUser user = dao.validateLogin(userId, password);
		AuthenticationDto token = null;
		if (user != null)
			token = this.makeAuthDto(user);
		return token;
	}

	private AuthenticationDto makeAuthDto(DocUser user) {
		return (new AuthenticationDto(user.getUserid(), user.getName(), user.getAbout()));
	}
}
