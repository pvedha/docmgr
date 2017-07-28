package com.doc.utilities;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.doc.exceptions.DocMgrException;

public class Utilities {

	/**
	 * Pass the date as string, the format is strictly maintained as yyyy-MM-dd as of now
	 * @param dateString in yyyy-MM-dd format
	 * @return
	 */
	public static Timestamp getTimeStamp(String dateString){		
		try{
		    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		    Date parsedDate = dateFormat.parse(dateString);
		    return new java.sql.Timestamp(parsedDate.getTime());
		}catch(Exception e){//this generic but you can control another types of exception
			throw new DocMgrException();
		}		
	}
	
	public static String getTextSearchQuery(String fieldName, String searchText){
		return " upper(" + fieldName + ") like upper(\'%" + searchText + "%\')";
	}
	
	public static String getDD_MMM_YYYY(Timestamp timestamp){
		return new SimpleDateFormat("dd-MMM-yyyy").format(timestamp);
	}
	
	public static String getDDMMYY_HHMM(Timestamp timestamp){
		return new SimpleDateFormat("dd/MMM/yy hh:mm").format(timestamp);
	}
	
	public static Timestamp getNow(){
		return new Timestamp(System.currentTimeMillis());
	}
}
