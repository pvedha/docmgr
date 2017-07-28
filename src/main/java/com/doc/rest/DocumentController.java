package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.dto.DocumentDto;
import com.doc.mgr.DocumentManager;

@Path("/doc")
public class DocumentController {
	private DocumentManager mgr = new DocumentManager();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllDocuments() {
		return Response.ok().entity(mgr.readAllDocuments()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/allOpen")
	public Response readAllOpenDocuments() {
		return Response.ok().entity(mgr.readAllOpenDocuments()).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/myDocs/{userId}")
	public Response readAllMyDocuments(@PathParam("userId") String userId) {
		return Response.ok().entity(mgr.readAllMyDocuments(userId)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/myOpenDocs/{userId}")
	public Response readMyOpenDocuments(@PathParam("userId") String userId) {
		return Response.ok().entity(mgr.readMyOpenDocuments(userId)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addDocument(DocumentDto dto){
		return Response.ok().entity(mgr.addDocument(dto)).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateDocument(DocumentDto dto){
		return Response.ok().entity(mgr.updateDocument(dto)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/states")
	public Response readDocStates() {
		return Response.ok().entity(mgr.readDocStates()).build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/test/add")
	public Response addDocument(){
		DocumentDto dto = new DocumentDto();
		dto.setChildId(2);
		dto.setDocName("somefile.doc");
		dto.setCreator("admin");
		dto.setOwner("w");
		dto.setStatus("Draft");
		dto.setRemarks("An excel sheet");
		return Response.ok().entity(mgr.addDocument(dto)).build();
	}
}
