package com.doc.utilities;

public class QueryStatements {
	
	//TODO ideally static finals should be ALL_CAPS	
	
	public static final String allMyDocsQuery = "select * from document where owner = :owner or creator = :creator";
	public static final String allDocsQuery = "select * from document";
	public static final String myOpenDocsQuery = "select * from document where (owner = :owner or creator = :creator) and status != 'Final'";
	public static final String allOpenDocsQuery = "select * from document where  status != 'Final'";
	
	public static final String getPropertiesQuery = "select * from properties";
	public static final String getJobTitlesQuery = "select * from jobtitles where title != 'Administrator'";
		
	
	
	
}
