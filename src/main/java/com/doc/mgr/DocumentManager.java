package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.DocState;
import com.doc.api.Document;
import com.doc.dao.DocumentDaoImpl;
import com.doc.dto.DocumentDto;
import com.doc.utilities.Utilities;

public class DocumentManager extends DocManager {

	public static final DocumentDaoImpl dao = new DocumentDaoImpl();
	
	public int addDocument(DocumentDto docDto){
		return dao.addDocument(docDto);
	}
	
	public ArrayList<DocumentDto> readAllDocuments(){
		return getDocumentDtos(dao.readAllDocuments());
	}
	
	public int updateDocument(DocumentDto dto){
		return dao.updateDocument(dto);
	}
	
	public ArrayList<DocState> readDocStates(){
		return dao.readDocStates();
	}
	public ArrayList<DocumentDto> getDocumentDtos(ArrayList<Document> docs){
		ArrayList<DocumentDto> dtos = new ArrayList<>();
		for(Document doc : docs){
			DocumentDto dto = new DocumentDto();
			dto.setChildId(doc.getChild().getId());
			dto.setChildName(doc.getChild().getName());
			dto.setCreatedOn(Utilities.getDDMMYY_HHMM(doc.getCreated_on()));
			dto.setCreator(doc.getCreator().getName());
			dto.setDocId(doc.getDocId());
			dto.setDocName(doc.getDocName());
			dto.setLastUpdated(Utilities.getDDMMYY_HHMM(doc.getLast_updated()));
			dto.setOwner(doc.getOwner().getName());
			dto.setRemarks(doc.getRemarks());
			dto.setRevision(doc.getRevision());
			dto.setStatus(doc.getStatus().getDocState());
			dtos.add(dto);
		}
		return dtos;
	}
	
	
	
}
