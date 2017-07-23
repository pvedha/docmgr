package com.doc.mgr;

import com.doc.dao.OracleDaoImpl;


/**
 * 
 * Super class for multiple manager, not just for document class
 * @author pvedha
 *
 */
public class DocManager {
	public static final OracleDaoImpl dao = new OracleDaoImpl();
}
