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
	protected int docrights;
	protected int childrights;
	protected int jobrights;	
	
	//TODO Find alternative, this is a stupid way of having the properties
	protected boolean viewAllActions;
	protected boolean addStaff;
	protected boolean addChildren;
	protected boolean viewAllChildren;
	protected boolean manageUserControls;
	protected boolean manageSettings;	
	
}
