package com.doc.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.doc.dto.AuthenticationDto;
import com.doc.dto.UserDto;
import com.doc.exceptions.DuplicateUserException;
import com.doc.exceptions.InvalidUserException;
import com.doc.mgr.UserManager;
import com.doc.utilities.Logger;

@Path("/user")
public class UserController {
	private UserManager mgr = new UserManager();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/all")
	public Response readAllUsers() {
		return Response.ok().entity(mgr.readAllUsers()).build();
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userid}/{password}")
	public Response login(@PathParam("userid") String userId, @PathParam("password") String password) {
		AuthenticationDto token = mgr.validateLogin(userId, password);
		if (token != null) {
			return Response.ok().entity(token).build();
		} else {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/validate")
	public Response validateSession(@HeaderParam("userId") String userId, @HeaderParam("token") String token) {
		AuthenticationDto response = mgr.validateSession(userId, token);
		if (response == null) {
			return Response.status(Status.FORBIDDEN).build();
		}
		return Response.ok().entity(response).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/addStaff")
	public Response addStaff(UserDto userDto) throws InvalidUserException, DuplicateUserException {	
			int number = mgr.addStaff(userDto);			
			return Response.ok().entity(Integer.toString(number)).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/update")
	public Response updateMyProfile(AuthenticationDto dto) throws InvalidUserException {	
			int number = mgr.updateMyProfile(dto);			
			return Response.ok().entity(Integer.toString(number)).build();
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("/reset/{userId}")
	public Response resetPassword(@PathParam("userId") String userId) throws InvalidUserException {	
			int number = mgr.resetPassword(userId);	
			return Response.ok().entity(Integer.toString(number)).build();
	}
}