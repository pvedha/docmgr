package com.doc.dto;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.doc.api.Children;
import com.doc.api.DocUser;
import com.doc.utilities.Utilities;

import lombok.Data;

@Data
public class ChildrenDto {
	private int id;
	private String name;
	private String dob;
	private String doj;
	private String remarks;
	private String message;
	private String teacher;
	private String councillor;
	private String therapist;
	private String tags;
	
	public ChildrenDto(){
		
	}

	public ChildrenDto(Children child){
		this.id = child.getId();
		this.name = child.getName();
		this.dob = new SimpleDateFormat("dd-MMM-yyyy").format(child.getDob());
		this.doj = new SimpleDateFormat("dd-MMM-yyyy").format(child.getDoj());
		this.remarks = child.getRemarks();
		this.message = child.getMessage();
		this.teacher = child.getTeacherName();
		this.councillor = child.getCouncillorName();
		this.therapist = child.getTherapistName();
		this.tags = child.getTags();
	}	
	
}
