package com.doc.exceptions;

@SuppressWarnings("serial")
public class ChildNotFoundException extends DocMgrException {
	public ChildNotFoundException(String message) {
		super(message);
	}
}
