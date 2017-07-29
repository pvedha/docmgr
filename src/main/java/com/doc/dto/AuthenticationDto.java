package com.doc.dto;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import com.doc.api.DocUser;
import com.doc.utilities.Logger;

import lombok.Data;

@Data
public class AuthenticationDto {

	private String userId;
	private String name;
	private String password;
	private String about;
	private String token;
	private String email;
	private String phone;
	private String jobTitle;	
	private int myOpenActionCount;
	
	public String genToken(String userId) {
		
		MessageDigest m;
		try {
			m = MessageDigest.getInstance("MD5");
			m.reset();
			m.update(userId.getBytes());
			byte[] digest = m.digest();
			BigInteger bigInt = new BigInteger(1,digest);
			String hashtext = bigInt.toString(16);
			// Now we need to zero pad it if you actually want the full 32 chars.
			while(hashtext.length() < 32 ){
			  hashtext = "0"+hashtext;
			}
			return hashtext;
		} catch (NoSuchAlgorithmException e) {
			Logger.log(e.getMessage());
			return null;
		}
		
	}
	
	public AuthenticationDto(DocUser user){
		setUserDetails(user);
	}
	
	public AuthenticationDto(DocUser user, Integer actionCount){		
		this.myOpenActionCount = actionCount;
		setUserDetails(user);
	}
	
	
	public AuthenticationDto() {
		// do nothing, needed for bean		
	}
	
	private void setUserDetails(DocUser user){
		this.userId = user.getUserid();
		this.name = user.getName();
		this.about = user.getAbout();
		this.setToken(this.genToken(userId));
		this.jobTitle = user.getJobTitleString();
		this.email = user.getEmail();
		this.phone = user.getPhone();
	}

}
