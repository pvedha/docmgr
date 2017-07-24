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
}
