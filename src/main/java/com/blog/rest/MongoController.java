package com.blog.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.blog.trials.MongoCheck;

@Path("/mongo")
public class MongoController {

	MongoCheck mc = new MongoCheck();
	
	public MongoController() {
		// TODO Auto-generated constructor stub
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/find/{userId}")
	public Response findUsers(@PathParam("userId") String userId){
		System.out.println();
		return Response.ok().entity(mc.findByUserId(userId)).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/users/all")
	public Response findUsers(){
		return Response.ok().entity(mc.getUsers()).build();
		
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/users/addSalary/{salary}")
	public Response incrementSalary(@PathParam("salary") int salary){
		return Response.ok().entity(mc.incrementSalary(salary)  + "").build();
		
	}

}
