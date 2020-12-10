package io.training.control.rest.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import io.training.boundary.UserService;
import io.training.control.rest.UserRESTServerEndpoint;
import io.training.entity.User;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class UserRESTServerEndpointImpl implements UserRESTServerEndpoint {
  @EJB private UserService userService;

  @Override
  public Response retrieveUser(long id) {
    User user = userService.find(id);
    if(user==null){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().entity(user).build();
  }

  @Override
  public Response getAllUsers(String phone) {

    List<User> usersList = userService.findAll();
    if(phone != null){
      return Response.ok().entity(usersList
              .stream()
              .filter((user) -> user.getPhone().equals(phone)).collect(Collectors.toList()))
              .build();
    }
    if(usersList.size()>0){
      return Response.ok().entity(usersList).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response getUserByEmail(String email) {
    Optional<User> user = userService.getUserByEmail(email);
    if(user.isPresent()){
      return Response.ok().entity(user.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response getUserByUsername(String username) {
    Optional<User> user = userService.getUserByUsername(username);
    if(user.isPresent()){
      return Response.ok().entity(user.get()).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response createUser(User user) {
    User createdUser = userService.save(user);
    if (createdUser==null){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().entity(createdUser).build();
  }


  @Override
  public Response editUser(long id, User user) {
    if(id != user.getId()){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    User FindUser = userService.find(id);
    if(FindUser == null){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    User editedUser = userService.update(user);
    return Response.ok().entity(editedUser).build();
  }



  @Override
  public Response deleteUser(long id) {
    User userDeleted = userService.find(id);
    if(userDeleted == null){
      return Response.status(Response.Status.NOT_FOUND).build();

    }
    return Response.ok().entity(userService.delete(userDeleted)).build();
  }
}
