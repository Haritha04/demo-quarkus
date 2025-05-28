package com.demo.controller;

import java.util.List;
import com.demo.entity.User;
import com.demo.exception.UserNotFoundException;
import com.demo.model.UserDTO;
import com.demo.service.UserService;

import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    private final UserService userService;

    @Inject
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    public Response getUsers() {
        return Response.ok(userService.getUsers()).build();
    }

    @GET
    @Path("/{id}")
    public Response getUserById(@PathParam("id") int id) throws UserNotFoundException {
        return Response.ok(userService.getUserById(id)).build();
    }

    @POST
    @Path("/create")
    public Response createUser(@Valid UserDTO userDto) {
        return Response.ok(userService.saveUser(userDto.toUser())).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") int id, @Valid UserDTO userDTO) throws UserNotFoundException {
        return Response.ok(userService.updateUser(id, userDTO.toUser())).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") int id) throws UserNotFoundException {
        userService.deleteUser(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
