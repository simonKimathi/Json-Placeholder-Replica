package io.training.control.rest.endPoints.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import io.training.boundary.UserService;
import io.training.control.rest.endPoints.UserRESTServerEndpoint;
import io.training.entity.User;
import io.training.util.DeleteStatus;

import java.net.URI;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class UserRESTServerEndpointImpl implements UserRESTServerEndpoint {
  @EJB private UserService userService;

  @Override
  public Response retrieveUser(long id) {
    Optional<User> optionalUser = userService.findById(id);
    return optionalUser.isPresent() ?
            Response.ok().entity(optionalUser
                    .stream()
                    .filter((user) -> user.getDeleteStatus().equals(DeleteStatus.AVAILABLE)).collect(Collectors.toList())
                    .get(0)).build()
            :
            Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response getAllUsers(String phone,String username,String email) {

    List<User> usersList = userService.findAll();
    if(phone != null){
      return Response.ok().entity(usersList
              .stream()
              .filter((user) -> user.getPhone().equals(phone)).collect(Collectors.toList()))
              .build();
    }
    if(username != null){
      return Response.ok().entity(usersList
              .stream()
              .filter((user) -> user.getUsername().equals(username)).collect(Collectors.toList()))
              .build();
    }
    if(email != null){
      return Response.ok().entity(usersList
              .stream()
              .filter((user) -> user.getEmail().equals(email)).collect(Collectors.toList()))
              .build();
    }
    if(usersList.size()>0){
      return Response.ok().entity(usersList).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response createUser(User user, UriInfo uriInfo) {
    if (userService.existsById(user.getId())) {
      return Response.status(Response.Status.CONFLICT).build();
    }
    User savedUser = userService.save(user);
    URI location = uriInfo.getBaseUriBuilder()
            .path(UserRESTServerEndpoint.class)
            .path(String.valueOf(savedUser.getId()))
            .build();
    return Response.created(location).entity(savedUser).build();

  }


  @Override
  public Response updateUser(long id, User user, UriInfo uriInfo) {
    if(id != user.getId()){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    User FindUser = userService.find(id);
    if(FindUser == null){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    User editedUser = userService.update(user);
    URI location = uriInfo.getBaseUriBuilder()
            .path(UserRESTServerEndpoint.class)
            .path(String.valueOf(editedUser.getId()))
            .build();
    return Response.created(location).entity(editedUser).build();

  }



  @Override
  public Response deleteUser(long id) {
    if(!userService.existsById(id)){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    userService.deleteById(id);
    return Response.ok().build();
  }
}
