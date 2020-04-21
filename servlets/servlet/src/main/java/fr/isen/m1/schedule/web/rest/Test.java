package fr.isen.m1.schedule.web.rest;

import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import fr.isen.m1.schedule.ejbs.ejbinterface.AlgorithmInterface;

@Path("test")
public class Test {

    @EJB(mappedName = "AlgorithmInterface")
    AlgorithmInterface algo;

    @GET
    public String testMethod() {
        return algo.helloWord();
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