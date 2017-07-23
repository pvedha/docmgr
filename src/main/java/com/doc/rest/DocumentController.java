package com.doc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.doc.dto.DocumentDto;
import com.doc.mgr.ActionManager;
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
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/add")
	public Response addDocument(DocumentDto dto){
		return Response.ok().entity(mgr.addDocument(dto)).build();
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
