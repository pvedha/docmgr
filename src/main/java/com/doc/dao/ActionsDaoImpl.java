package com.doc.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;

import com.doc.api.ActionStates;

@SuppressWarnings("unchecked")
public class ActionsDaoImpl extends DaoImpl {
	
	public ArrayList<ActionStates> readActionStates() {
		EntityManager em = factory.createEntityManager();
		ArrayList<ActionStates> actionStates = (ArrayList<ActionStates>) em
				.createNativeQuery("select * from actionstates", ActionStates.class).getResultList();
		em.close();
		return actionStates;
	}
}
