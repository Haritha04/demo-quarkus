package com.demo.controller;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import com.demo.entity.User;
import com.demo.exception.UserNotFoundException;
import com.demo.model.UserDTO;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {
    private static final SortedSet<User> dummyUserList = new TreeSet<>();
    static {
        dummyUserList.addAll(Set.of(createDummyUser(1, "Haritha", "Anasuri", "haritha.a@imaginnovate.com"),
                createDummyUser(2, "Govind", "Ch", "Govind.ch@imaginnovate.com"),
                createDummyUser(3, "Raj", "Kumar", "rajkumar@imaginnovate.com"),
                createDummyUser(4, "Vijay", "S", "Vijay.s@imaginnovate.com"),
                createDummyUser(5, "Anasuya", "P", "anasuya.p@imaginnovate.com")));
    }

    private static User createDummyUser(int id, String firstName, String lastName, String email) {
        User user = new User();
        user.setId(id);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        return user;
    }

    @GET
    public Set<User> getUsers() {
        return dummyUserList;
    }

    @GET
    @Path("/{id}")
    public User getUserById(int id) throws UserNotFoundException {
        return dummyUserList.stream().filter(user -> user.getId() == id).findFirst()
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
    }

    @POST
    @Path("/create")
    public User createUser(@Valid UserDTO userDto) {
        User user = createDummyUser(dummyUserList.last().getId() + 1, userDto.getFirstName(), userDto.getLastName(),
                userDto.getEmail());
        dummyUserList.add(user);
        return user;
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(int id) throws UserNotFoundException {
        dummyUserList.remove(getUserById(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
