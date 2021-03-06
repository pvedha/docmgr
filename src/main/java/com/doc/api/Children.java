package com.doc.api;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

@Entity
@XmlRootElement
@Data
public class Children {
	@Id
	//@GeneratedValue(strategy = GenerationType.AUTO)
	protected int id;
	protected String name;
	protected Timestamp dob;
	protected Timestamp doj;
	protected String remarks;
	protected String message;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "teacher", referencedColumnName = "userid")
	protected DocUser teacher;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "councillor", referencedColumnName = "userid")
	protected DocUser councillor;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "therapist", referencedColumnName = "userid")
	protected DocUser therapist;
	protected String tags;

	public String getTeacherName() {
		return teacher.getName();
	}
	
	public String getCouncillorName() {
		return councillor.getName();
	}
	
	public String getTherapistName() {
		return therapist.getName();
	}
	
	public String getTeacherUserId() {
		return teacher.getUserid();
	}
	
	public String getCouncillorUserId() {
		return councillor.getUserid();
	}
	
	public String getTherapistUserId() {
		return therapist.getUserid();
	}
}
