package io.training.control.rest.impl;

import io.training.boundary.PostService;
import io.training.control.rest.PostRESTEndpoint;
import io.training.entity.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

@Stateless
public class PostRESTEndpointImpl implements PostRESTEndpoint {
  @EJB
  private PostService postService;

  @Override
  public Response listAllPosts(long userId) {
    return userId > 0 ? Response.ok(postService.findAllByUserId(userId)).build()
            : Response.ok(postService.findAll()).build();

  }

  @Override
  public Response createPost(Post post, UriInfo uriInfo) {
    if (postService.existsById(post.getId())) {
      return Response.status(Response.Status.CONFLICT).build();
    }
    Post savedPost = postService.save(post);
    URI location = uriInfo.getBaseUriBuilder()
            .path(PostRESTEndpoint.class)
            .path(String.valueOf(savedPost.getId()))
            .build();
    return Response.created(location).entity(savedPost).build();
  }

  @Override
  public Response getPostById(long id) {
    Optional<Post> optionalPost = postService.findById(id);
    return optionalPost.isPresent() ?
            Response.ok().entity(optionalPost.get()).build()
            :
            Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response updatePost(long id, Post post, UriInfo uriInfo) {
    if (!postService.existsById(id)) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    Post updatePost = postService.update(post);
    URI location = uriInfo.getBaseUriBuilder()
            .path(PostRESTEndpoint.class)
            .path(String.valueOf(updatePost.getId()))
            .build();
    return Response.created(location).entity(updatePost).build();

  }

  @Override
  public Response deletePost(long id) {
    if (!postService.existsById(id)) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    postService.deleteById(id);
    return Response.ok().build();
  }
}

