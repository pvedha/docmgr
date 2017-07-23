package com.doc.api;

import java.sql.Timestamp;

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
public class Document {
	@Id
	protected int docId;
	protected String docName;
	protected int revision;
	@FormParam("child")
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "childId", referencedColumnName="id")		
	protected Children child;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "owner", referencedColumnName = "userid")	
	protected DocUser owner;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "creator", referencedColumnName = "userid")
	protected DocUser creator;	
	protected Timestamp created_on;	
	protected Timestamp last_updated;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "status", referencedColumnName = "docState")
	protected DocState status;
	
}
