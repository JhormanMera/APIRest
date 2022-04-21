package services;

import entity.User;
import model.Message;
import provider.UserProvider;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("user")
public class UserService {
    @GET
    @Path("all/{page}")
    @Produces("application/json")
    public Response getAll(@PathParam("page") int page) {
        try {
            UserProvider uProv = new UserProvider();
            ArrayList<User> users = uProv.getAllUsers(page);
            return Response.status(200).entity(users).build();
        } catch (Exception ex) {
            Message m = new Message("Exception",ex.getMessage());
            return Response.status(500).entity(m).build();
        }
    }

    @POST
    @Path("create")
    @Consumes("application/json")
    @Produces("application/json")
    public Response createObject(User user){
        try {
            UserProvider uProv = new UserProvider();
            uProv.create(user);
            return Response.status(200).entity(new Message("info","Operación exitosa")).build();
        } catch (Exception ex) {
            Message m = new Message("Exception",ex.getMessage());
            return Response.status(200).entity(new Message("Exception",ex.getMessage()+" Fallido....")).build();
        }

    }
}
