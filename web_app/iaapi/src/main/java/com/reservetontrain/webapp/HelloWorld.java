package com.reservetontrain.webapp;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {
    @GET
    @Path("/{param}/{p2}")
    public Response getMessage(@PathParam("param") String message,@PathParam("p2") String amessage) {
        String output = "Jersey says " + message + " "+ amessage;
        return Response.status(200).entity(output).build();
    }
}