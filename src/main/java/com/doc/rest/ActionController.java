package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.mgr.ActionManager;

@Path("/action")
public class ActionController {
	private ActionManager mgr = new ActionManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readActionStates() {
		return Response.ok().entity(mgr.readActionStates()).build();
	}
}
