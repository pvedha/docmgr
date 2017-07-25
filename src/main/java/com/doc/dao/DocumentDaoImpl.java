package com.doc.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.doc.api.ActionStates;
import com.doc.api.Actions;
import com.doc.api.Children;
import com.doc.api.DocState;
import com.doc.api.DocUser;
import com.doc.api.Document;
import com.doc.dto.ActionDto;
import com.doc.dto.DocumentDto;
import com.doc.exceptions.ActionItemNotFoundException;
import com.doc.exceptions.ChildNotFoundException;
import com.doc.exceptions.DocumentNotFoundException;
import com.doc.exceptions.InvalidStatusException;
import com.doc.exceptions.StaffNotFoundException;
import com.doc.utilities.Utilities;

@SuppressWarnings("unchecked")
public class DocumentDaoImpl extends DaoImpl {

	public ArrayList<Document> readAllDocuments() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Document> documents = (ArrayList<Document>) em
				.createNativeQuery("select * from Document", Document.class).getResultList();
		em.close();
		return documents;
	}

	public int addDocument(DocumentDto docDto) {
		EntityManager em = factory.createEntityManager();
		Children child = em.find(Children.class, docDto.getChildId());
		if ( child == null) {
			throw new ChildNotFoundException("Id " + docDto.getChildId() + " not found");
		}		
		DocUser owner = em.find(DocUser.class, docDto.getOwner());
		if (owner == null) {
			throw new StaffNotFoundException("Teacher : " + docDto.getOwner() + " not found");
		}
		DocUser creator = em.find(DocUser.class, docDto.getCreator());
		if (creator == null) {
			throw new StaffNotFoundException("Councillor : " + docDto.getCreator() + " not found");
		}

		DocState state = em.find(DocState.class, docDto.getStatus());
		if (state == null) {
			throw new InvalidStatusException("State : " + docDto.getStatus() + " not found");
		}
		
		Document doc = new Document();
		doc.setChild(child);
		doc.setCreated_on(Utilities.getNow());
		doc.setLast_updated(Utilities.getNow());
		doc.setCreator(creator);
		doc.setOwner(owner);
		doc.setDocName(docDto.getDocName());
		doc.setRevision(1);
		doc.setRemarks(docDto.getRemarks());
		doc.setStatus(state);
		
		persistObject(doc);
		em.close();
		return doc.getDocId();
	}
	
	
	public int updateDocument(DocumentDto dto) {
		EntityManager em = factory.createEntityManager();		
		
		Document doc = em.find(Document.class, dto.getDocId());
		if (doc == null) {
			throw new DocumentNotFoundException(dto.getDocId() + "");
		}
		DocState state = em.find(DocState.class, dto.getStatus());
		if (state == null) {
			throw new InvalidStatusException("State : " + dto.getStatus() + " not found");
		}
		DocUser owner = em.find(DocUser.class, dto.getOwner());
		if (owner == null) {
			throw new StaffNotFoundException("Teacher : " + dto.getOwner() + " not found");
		}
		
		doc.setLast_updated(Utilities.getNow());
		doc.setOwner(owner);
		doc.setRemarks(dto.getRemarks());
		doc.setRevision(doc.getRevision());
		doc.setStatus(state);
		
		em.getTransaction().begin();
		em.persist(doc);
		em.getTransaction().commit();
		em.close();

		return doc.getDocId();
	}
	
	public ArrayList<DocState> readDocStates(){
		EntityManager em = factory.createEntityManager();
		ArrayList<DocState> docStates = (ArrayList<DocState>) em.createNativeQuery("select * from docstate", DocState.class).getResultList();
		em.close();
		return docStates;
	}
	

}
