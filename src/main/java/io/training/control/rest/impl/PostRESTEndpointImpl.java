package io.training.control.rest.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import io.training.boundary.PostService;
import io.training.control.rest.PostRESTEndpoint;
import io.training.entity.Post;
import io.training.entity.User;

@Stateless
public class PostRESTEndpointImpl implements PostRESTEndpoint {
  @EJB
  private PostService postService;

  @Override
  public Response retrievePost(int id) {
    Post post = postService.find(id);
    if(post==null){
      Error error = new Error();
      ErrorInfo errorInfo =  new ErrorInfo();
      errorInfo.setBriefSummary("User not found");
      errorInfo.setStatusCode(400);
      errorInfo.setDescriptionURL("http://localhost:8080/TrainingApp/swagger-ui/#/Post/retrievePost");
      error.setErrorInfo(errorInfo);
      return Response.status(Response.Status.NOT_FOUND).entity(error).build();
    }
    return Response.ok().entity(post).build();
  }

  @Override
  public Response createPost(Post post) {
    Post createdPost = postService.create(post);
    if (createdPost==null){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().entity(createdPost).build();
  }
}
