package com.doc.utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.doc.api.Jobtitle;

public class Environment {

	public Environment() {
		// TODO Auto-generated constructor stub
	}

	public static Map<String, String> actionStates = new HashMap<String, String>();
	public static Map<String, String> docStates = new HashMap<String, String>();	
	public static Map<String, String> properties = new HashMap<String, String>();
	public static List<Jobtitle> jobTitles = new ArrayList<Jobtitle>();
	
	public static final String ADMIN_NAME = "admin";
	public static final String ADMINISTRATOR = "Administrator"; 	
	
	public static final String ACTIONSTATE_CREATED = "Created";
	public static final String ACTIONSTATE_ACTIONED = "Actioned";
	public static final String ACTIONSTATE_ACCEPTED = "Accepted";
	public static final String ACTIONSTATE_CLOSED = "Closed";
	
	public static final String ACTIONSTATE_CREATED_REMARKS = "Action Item Created";
	public static final String ACTIONSTATE_ACTIONED_REMARKS = "Action Item completed by owner";
	public static final String ACTIONSTATE_ACCEPTED_REMARKS = "Action Item completed by owner";
	public static final String ACTIONSTATE_CLOSED_REMARKS = "Action Item accepted and closed by creator";
	
	public static final String DOCSTATE_DRAFT = "Draft";
	public static final String DOCSTATE_INPROGRESS = "In-progress";
	public static final String DOCSTATE_FINAL = "Final";
	
	public static final String DOCSTATE_DRAFT_REMARKS = "Document created or in draft state";
	public static final String DOCSTATE_INPROGRESS_REMARKS = "Document work in progress";
	public static final String DOCSTATE_FINAL_REMARKS = "Document marked as final, no more change expected";
	
	public static final String BASEPATH = "BasePath";
	public static final String BASEPATH_VALUE = "c:/temp";
	
	
	
	static {
		actionStates.put(ACTIONSTATE_CREATED, ACTIONSTATE_CREATED_REMARKS);
		actionStates.put(ACTIONSTATE_ACTIONED, ACTIONSTATE_ACTIONED_REMARKS);
		actionStates.put(ACTIONSTATE_ACCEPTED, ACTIONSTATE_ACCEPTED_REMARKS);
		actionStates.put(ACTIONSTATE_CLOSED, ACTIONSTATE_CLOSED_REMARKS);
		
		docStates.put(DOCSTATE_DRAFT, DOCSTATE_DRAFT_REMARKS);
		docStates.put(DOCSTATE_INPROGRESS, DOCSTATE_INPROGRESS_REMARKS);
		docStates.put(DOCSTATE_FINAL, DOCSTATE_FINAL_REMARKS);
		
		properties.put(BASEPATH, BASEPATH_VALUE);
		
		jobTitles.add(new Jobtitle("Teacher", "Teacher", true, true, true, true, true, false, false));
		jobTitles.add(new Jobtitle("Therapist", "Therapist", true, true, true, true, true, false, false));
		jobTitles.add(new Jobtitle("Counsellor", "Counsellor", true, true, true, true, true, false, false));
	}
	
}
