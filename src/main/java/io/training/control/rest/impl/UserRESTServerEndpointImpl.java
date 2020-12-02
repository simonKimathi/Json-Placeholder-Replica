package io.training.control.rest.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import io.training.boundary.UserService;
import io.training.control.rest.UserRESTServerEndpoint;
import io.training.entity.User;

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
  public Response createUser(User user) {
    User createdUser = userService.create(user);
    if (createdUser==null){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().entity(createdUser).build();
  }
}
