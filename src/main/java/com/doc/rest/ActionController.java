package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.dto.ActionDto;
import com.doc.dto.DocumentDto;
import com.doc.mgr.ActionManager;

@Path("/action")
public class ActionController {
	private ActionManager mgr = new ActionManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readActions() {
		return Response.ok().entity(mgr.readActions()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/states/all")
	public Response readActionStates() {
		return Response.ok().entity(mgr.readActionStates()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateAction(ActionDto dto){
		return Response.ok().entity(mgr.updateAction(dto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addAction(ActionDto dto){
		return Response.ok().entity(mgr.addAction(dto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/add")
	public Response addDocument(){
		ActionDto dto = new ActionDto();
		
		dto.setDocId(1);
		dto.setActionTitle("Fill the latest assessment results");
		dto.setAction_creator("p");
		dto.setAction_owner("p");
		dto.setRemarks("trial addition");
		dto.setState("Closed");
		
		return Response.ok().entity(mgr.addAction(dto)).build();
	}
}
