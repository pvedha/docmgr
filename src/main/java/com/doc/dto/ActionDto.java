package com.doc.dto;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.doc.api.DocUser;
import com.doc.api.Document;

import lombok.Data;

@Data
public class ActionDto {

	protected int actionId;
	protected int docId;
	protected String action_creator;
	protected String action_owner;
	protected String created_on;
	protected String updated_on;
	protected String state;
	protected String remarks;

	public ActionDto(){
		
	}
}
