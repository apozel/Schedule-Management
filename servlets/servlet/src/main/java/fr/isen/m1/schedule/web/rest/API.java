package fr.isen.m1.schedule.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;

@Path("API")
public class API {

    @EJB(mappedName = "AlgorithmInterface")
    AlgorithmInterface algo;

    @GET
    @Path("algo")
    public String testMethod() {
        return algo.helloWord() + "2";
    }

    @GET
    @Path("test")
    public String testMethod2() {
        return "test2";
    }


   /* @Produces(MediaType.APPLICATION_JSON)
    @Path("team")
    public Response team() {
        JsonArrayBuilder teamJsonArray = Json.createArrayBuilder();
        for (String[] strings : team) {
            teamJsonArray.add(Json.createObjectBuilder().add(strings[0], strings[1]));
        }

        return Response.ok(teamJsonArray.build(), MediaType.APPLICATION_JSON).build();
    }
*/
}