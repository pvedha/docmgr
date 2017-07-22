package com.doc.dto;

import com.doc.api.DocUser;

import lombok.Data;

@Data
public class UserDto {

	private String userId;
	private String userName;
	private String about;
	private String email;
	private String phone;
	private String jobTitle;
	
	public UserDto(DocUser docUser){
		this.userId = docUser.getUserid();
		this.userName = docUser.getName();
		this.about = docUser.getAbout();
		this.email = docUser.getEmail();
		this.phone = docUser.getPhone();
		this.jobTitle = docUser.getJobtitle().getTitle();
	}

	public UserDto(){
		
	}
}
