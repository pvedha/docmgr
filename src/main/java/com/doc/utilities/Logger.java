package com.doc.utilities;

public class Logger {
	
	private Logger(){
		//do nothing for sonarqube
	}
	public static void log(String message){
		System.out.println(message);
	}

	public static void log(int message){
		System.out.println(message);
	}
}
