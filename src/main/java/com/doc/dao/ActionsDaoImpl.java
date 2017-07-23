package com.doc.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.doc.api.ActionStates;
import com.doc.api.Actions;
import com.doc.api.DocUser;
import com.doc.api.Document;
import com.doc.dto.ActionDto;
import com.doc.exceptions.DocumentNotFoundException;
import com.doc.exceptions.InvalidStatusException;
import com.doc.exceptions.StaffNotFoundException;
import com.doc.utilities.Utilities;

@SuppressWarnings("unchecked")
public class ActionsDaoImpl extends DaoImpl {
	
	public ArrayList<Actions> readActions() {
		EntityManager em = factory.createEntityManager();
		ArrayList<Actions> actions = (ArrayList<Actions>) em
				.createNativeQuery("select * from actions", Actions.class).getResultList();
		
		em.close();
		return actions;
	}
	
	public ArrayList<ActionStates> readActionStates() {
		EntityManager em = factory.createEntityManager();
		ArrayList<ActionStates> actionStates = (ArrayList<ActionStates>) em
				.createNativeQuery("select * from actionstates", ActionStates.class).getResultList();
		em.close();
		return actionStates;
	}
	
	public int addAction(ActionDto dto) {
		EntityManager em = factory.createEntityManager();
		Document doc = em.find(Document.class, dto.getDocId());
		if (doc == null) {
			throw new DocumentNotFoundException("Document : " + dto.getDocId() + " not found");
		}
		DocUser owner = em.find(DocUser.class, dto.getAction_owner());
		if (owner == null) {
			throw new StaffNotFoundException("Owner : " + dto.getAction_owner() + " not found");
		}
		DocUser creator = em.find(DocUser.class, dto.getAction_creator());
		if (creator == null) {
			throw new StaffNotFoundException("Creator : " + dto.getAction_creator() + " not found");
		}		
		ActionStates state = em.find(ActionStates.class, dto.getState());
		if (state == null) {
			throw new InvalidStatusException("State : " + dto.getState() + " not found");
		}
		
		Actions action = new Actions();
		action.setDocument(doc);
		action.setAction_creator(creator);
		action.setAction_owner(owner);
		action.setAction_title(dto.getActionTitle());
		action.setCreated_on(Utilities.getNow());
		action.setUpdated_on(Utilities.getNow());		
		action.setState(state);
		action.setRemarks(dto.getRemarks());
		
		persistObject(action);
		
		return action.getActionId();
	}
}
