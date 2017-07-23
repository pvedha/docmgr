package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.ActionStates;
import com.doc.api.Document;
import com.doc.dao.ActionsDaoImpl;
import com.doc.dao.DocumentDaoImpl;
import com.doc.dto.DocumentDto;

public class DocumentManager extends DocManager {

	public static final DocumentDaoImpl dao = new DocumentDaoImpl();
	
	public int addDocument(DocumentDto docDto){
		return dao.addDocument(docDto);
	}
	
	public ArrayList<Document> readAllDocuments(){
		return dao.readAllDocuments();
	}
}
