package com.doc.api;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@XmlRootElement
@Data
public class Actions {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	protected int actionId;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "document", referencedColumnName = "docId")
	protected Document document;
	protected String action_title;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "action_creator", referencedColumnName = "userid")	
	protected DocUser action_creator;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "action_owner", referencedColumnName = "userid")
	protected DocUser action_owner;
	protected Timestamp created_on;
	protected Timestamp updated_on;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "state", referencedColumnName = "state")
	protected ActionStates state;
	protected String remarks;
}
