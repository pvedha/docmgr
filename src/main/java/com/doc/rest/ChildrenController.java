package com.doc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.dto.ChildrenDto;
import com.doc.mgr.ChildrenManager;

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
	@Path("/add")
	public Response addChild(ChildrenDto childDto) {
			int number = mgr.addChild(childDto);
			return Response.ok().entity(Integer.toString(number)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateChild(ChildrenDto childDto) {
			return Response.ok().entity(mgr.updateChild(childDto) + "").build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find/{key}")
	public Response searchAllChildren(@PathParam("key") String key) {
		return Response.ok().entity(mgr.searchChildren(key)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/find/id/{id}")
	public Response findChildById(@PathParam("id") Integer id) {
		return Response.ok().entity(mgr.findChildById(id)).build();
	}	
	
}
