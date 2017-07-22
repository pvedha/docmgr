package com.doc.exceptions;

@SuppressWarnings("serial")
public class StaffNotFoundException extends DocMgrException {
	public StaffNotFoundException(String message) {
		super(message);
	}
}
