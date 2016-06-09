package com.reservetontrain.webapp;

import com.reservetontrain.ia.Ia;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * Created by titouanfreville on 09/06/16.
 */
@Path("/getshortest")
public class GetShortest {
    @GET
    @Path("/{dep}/{arr}")
    @Produces("application/json")
    public Response getShortest(@PathParam("dep") String dep, @PathParam("arr") String arr) {
        JSONObject jsonr;
        Ia ia = new Ia("http://51.255.1.76:3000/api/");
        jsonr=ia.map_to_json(ia.getshortesttrips(dep,arr));
        return Response.status(200).entity(jsonr.toString()).build();
    }
}
