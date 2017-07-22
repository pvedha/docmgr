package com.doc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.doc.dto.ChildrenDto;
import com.doc.dto.UserDto;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.InvalidUserException;
import com.doc.mgr.ChildrenManager;
import com.doc.mgr.UserManager;
import com.doc.utilities.Logger;

@Path("/child")
public class ChildrenController {
	private ChildrenManager mgr = new ChildrenManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllChildren() {
		return Response.ok().entity(mgr.readAllChildren()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addChild")
	public Response addStaff(ChildrenDto childDto) throws InvalidUserException, DuplicateUserException {	
		try {
			int number = mgr.addChild(childDto);			
			return Response.ok().entity(Integer.toString(number)).build();
		} catch (Exception e) {
			return Response.status(Status.CONFLICT).entity("Invalid User Details").build();
		}
	}
}
