package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.mgr.UserManager;

@Path("/gen")
public class GenericController {

	private UserManager mgr = new UserManager();
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/init")
	public Response initSystem() {
		mgr.initDB();
		return Response.ok().entity("Generic").build();
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/initTry")
	public Response initTrial() {
		//mgr.initTrial();
		return Response.ok().entity("GenericTrial").build();
	}	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/jobTitles")
	public Response getJobTitles() {
		
		return Response.ok().entity(mgr.getJobTitles()).build();
	}	
	
	
	
}
