package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.ActionStates;
import com.doc.api.Actions;
import com.doc.dao.ActionsDaoImpl;
import com.doc.dto.ActionDto;
import com.doc.dto.DocumentDto;
import com.doc.utilities.Utilities;

public class ActionManager extends DocManager {

	public static final ActionsDaoImpl dao = new ActionsDaoImpl();
	
//	public ArrayList<ActionDto> readActions(){
//		return dao.readActions();
//	}
	
	public ArrayList<ActionDto> readActions(){
		ArrayList<Actions> actions = dao.readActions();
		ArrayList<ActionDto> dtos = new ArrayList<>();
		for(Actions action : actions){
			ActionDto dto = new ActionDto();
			dto.setAction_creator(action.getAction_creator().getName());
			dto.setAction_owner(action.getAction_owner().getName());
			dto.setActionId(action.getActionId());
			dto.setActionTitle(action.getAction_title());
			dto.setCreated_on(Utilities.getDDMMYY_HHMM(action.getCreated_on()));
			dto.setUpdated_on(Utilities.getDDMMYY_HHMM(action.getUpdated_on()));
			dto.setDocId(action.getDocument().getDocId());
			dto.setDocName(action.getDocument().getDocName());
			dto.setRemarks(action.getRemarks());
			dto.setState(action.getState().getState());
			dtos.add(dto);
		}		
		return dtos;
	}
	
	public ArrayList<ActionStates> readActionStates(){
		return dao.readActionStates();
	}
	
	public int addAction(ActionDto dto){
		return dao.addAction(dto);
	}
}
