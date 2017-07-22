package com.doc.rest;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import com.doc.exceptions.DocMgrException;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.InvalidUserException;
import com.doc.exceptions.JobTitleNotValidException;
import com.doc.exceptions.StaffNotFoundException;
import com.doc.utilities.Logger;

public class DocExceptionHandler implements ExceptionMapper<DocMgrException> {

	@Override
	public Response toResponse(DocMgrException e) {
 		Response.Status httpStatus = Response.Status.INTERNAL_SERVER_ERROR;
 		Logger.log("Exception mapper " + e.getMessage());
 		if(e instanceof InvalidUserException){
 			httpStatus = Response.Status.NOT_ACCEPTABLE;
 		} else if(e instanceof DuplicateUserException){
 			httpStatus = Response.Status.CONFLICT;
 		} else if(e instanceof JobTitleNotValidException){
 			httpStatus = Response.Status.NOT_ACCEPTABLE;
 		} else if(e instanceof StaffNotFoundException){
 			httpStatus = Response.Status.NOT_FOUND;
 		} 
 		
		return Response.status(httpStatus).entity(e.getMessage()).build();
	}

}
