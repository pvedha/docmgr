package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.mgr.UserManager;

@Path("/child")
public class ChildrenController {
	private UserManager mgr = new UserManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllChildren() {
		return Response.ok().entity(mgr.readAllChildren()).build();
	}
}
