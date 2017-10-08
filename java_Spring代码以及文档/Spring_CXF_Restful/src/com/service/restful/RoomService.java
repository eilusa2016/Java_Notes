package com.service.restful;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class RoomService {
	
	@GET
	@Path("/room/{id}")
	@Consumes("application/xml")
	public String getRoom(@PathParam("id") String id) {
		System.out.println("get room by id= " + id);
		return id;
	}

	

	@POST
	@Path("/room")
	@Consumes("application/xml")
	public void addRoom() {
		System.out.println("add room which id is:。。。");
		
	}

	@PUT
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void updateRoom(@PathParam("id") String id) {
		System.out.println("update room which original id is:");
		
	}

	@DELETE
	@Path("/room/{id}")
	@Consumes("application/xml")
	public void deleteRoom(@PathParam("id") String id) {
		System.out.println("remove room by id= " + id);
	}

	
}
