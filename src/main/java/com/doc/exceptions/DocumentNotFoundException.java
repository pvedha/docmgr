package com.doc.exceptions;

@SuppressWarnings("serial")
public class DocumentNotFoundException extends DocMgrException {
	public DocumentNotFoundException(String message) {
		super(message);
	}
}
