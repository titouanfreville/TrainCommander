package com.reservetontrain.rest;

import java.util.List;
import java.util.Vector;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.reservetontrain.ia.Ia;
import org.json.JSONException;
import org.json.JSONObject;


// Plain old Java Object it does not extend as class or implements
// an interface

// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.

// The browser requests per default the HTML MIME type.

//Sets the path to base URL + /hello
@Path("/getshortest")
public class GetShortest {
   /* @Path("{dep}/{arr}")*/
    @GET
    @Produces("application/json")
    /*@PathParam("dep") String dep, @PathParam("arr") String arr*/
    public Response getTrips() throws JSONException {
        JSONObject jsonr = new JSONObject();
        Ia ia = new Ia("http://51.255.1.76:3000/api/");
       // jsonr=ia.map_to_json(ia.getshortesttrips(dep,arr));
        return Response.status(200).entity("Je suis la"+jsonr).build();
    }
}
