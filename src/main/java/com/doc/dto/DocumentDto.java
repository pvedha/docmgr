package com.doc.dto;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class DocumentDto {

	protected int docId;
	protected String docName;
	protected int revision;
	protected int childId;
	protected String owner;
	protected String creator;	
	protected String created_on;	
	protected String last_updated;
	protected String status;
	
	public DocumentDto(){
		
	}
}
