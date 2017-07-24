package com.doc.dto;

import lombok.Data;

@Data
public class DocumentDto {

	protected int docId;
	protected String docName;
	protected int revision;
	protected int childId;
	protected String childName;
	protected String owner;
	protected String creator;	
	protected String createdOn;	
	protected String lastUpdated;
	protected String remarks;
	protected String status;
	
	public DocumentDto(){
		
	}
}
