package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.ActionStates;
import com.doc.dao.ActionsDaoImpl;
import com.doc.dto.ActionDto;
import com.doc.dto.DocumentDto;

public class ActionManager extends DocManager {

	public static final ActionsDaoImpl dao = new ActionsDaoImpl();
	
	public ArrayList<ActionStates> readActionStates(){
		return dao.readActionStates();
	}
	
	public int addAction(ActionDto dto){
		return dao.addAction(dto);
	}
}
