package com.doc.api;

import javax.persistence.CascadeType;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.ws.rs.FormParam;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@XmlRootElement
@Data
public class DocUser {
	@Id
	@FormParam("userid")
	protected String userid;
	@FormParam("name")
	protected String name;
	@FormParam("password")
	protected String password;
	@FormParam("about")
	protected String about;
	@FormParam("jobtitle")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "jobtitle", referencedColumnName="title")	
	protected Jobtitle jobtitle;

	public DocUser(){
		
	}


}
