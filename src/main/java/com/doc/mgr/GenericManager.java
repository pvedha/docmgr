package com.doc.mgr;

import java.util.ArrayList;

import com.doc.api.Jobtitle;
import com.doc.api.Properties;

public class GenericManager extends DocManager{

	//private OracleDaoImpl dao = OracleDaoImpl.getInstance(); 
	
	public void initSystem(){
		//dao.initDB();
	};
	
	public void initTrial(){
		dao.initTry();
	}
	
	public void initDB(){
		dao.initDB();
	}

	public ArrayList<Properties> getProperties(){
		return dao.getProperties();
	}
	public ArrayList<Jobtitle> getJobTitles() {
		return dao.getJobTitles();
	};
	
	public int updateJobTitle(Jobtitle dto){
		return dao.updateJobTitle(dto);
	}
	
}
