package com.doc.api;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@XmlRootElement
@Data
public class Jobtitle {
	@Id
	protected String title;
	protected String remarks;
	
		//TODO Find alternative, this is a stupid way of having the properties
	
	protected boolean addStaff;
	protected boolean addChildren;
	protected boolean viewAllChildren;
	protected boolean viewAllDocuments;
	protected boolean viewAllActions;
	protected boolean manageUserControls;
	protected boolean manageSettings;	
	
}
