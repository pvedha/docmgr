package com.doc.dto;

import lombok.Data;

@Data
public class ActionDto {

	protected int actionId;
	protected int docId;
	protected String docName;
	protected String actionTitle;
	protected String action_creator;
	protected String action_owner;
	protected String created_on;
	protected String updated_on;
	protected String state;
	protected String remarks;

	public ActionDto(){
		
	}
}
