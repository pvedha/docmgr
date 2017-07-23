package com.doc.exceptions;

@SuppressWarnings("serial")
public class InvalidStatusException extends DocMgrException {
	public InvalidStatusException(String message) {
		super(message);
	}
}
