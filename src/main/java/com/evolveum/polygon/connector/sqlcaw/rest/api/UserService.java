package com.evolveum.polygon.connector.sqlcaw.rest.api;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/sqlcaw/rest")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface UserService {

    @GET
    @Path("/users")
    RObjects<RUser> list(@QueryParam("query") String query);

    @GET
    @Path("/users/{id}")
    RUser get(@PathParam("id") String id);

    @POST
    @Path("/users")
    String add(RUser user);

    @DELETE
    @Path("/users/{id}")
    void delete(@PathParam("id") String id);

    @PUT
    @Path("/users")
    String update(RUser user);

    @GET
    @Path("/sync")
    RDeltas sync(@QueryParam("time") Long time);

    @GET
    @Path("/sync/token")
    Long latestToken();
}
