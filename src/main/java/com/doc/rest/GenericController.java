package com.doc.rest;

import java.util.ArrayList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.api.Jobtitle;
import com.doc.api.Properties;
import com.doc.mgr.GenericManager;

@Path("/gen")
public class GenericController {

	private GenericManager mgr = new GenericManager();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/init")
	public Response initSystem() {
		mgr.initTrial();
		return Response.ok().entity("Generic").build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/properties")
	public Response getProperties() {
		return Response.ok().entity(mgr.getProperties()).build();
	}
	
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Path("/properties")
//	public Response setProperties(ArrayList<Properties> properties) {
//		return Response.ok().entity(mgr.setProperties(properties)).build();
//		//TODO check why this is not showing int error? need +"" to make a string usually.
//	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/properties")
	public Response setProperty(Properties item) {
		return Response.ok().entity(mgr.setProperties(item)).build();
		//TODO check why this is not showing int error? need +"" to make a string usually.
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/jobTitles")
	public Response getJobTitles() {
		return Response.ok().entity(mgr.getJobTitles()).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/jobTitles/update")
	public Response updateJoTitle(Jobtitle dto){
		return Response.ok().entity(mgr.updateJobTitle(dto) + "").build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/jobTitles/add")
	public Response addJobTitle(Jobtitle dto){
		return Response.ok().entity(mgr.addJobTitle(dto) + "").build();
	}
	
	
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/initDB")
	public Response initTrial() {
		// mgr.initTrial();
		return Response.ok().entity("GenericTrial").build();
	}

	

}
