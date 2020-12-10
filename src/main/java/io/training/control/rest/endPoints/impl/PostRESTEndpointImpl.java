package io.training.control.rest.endPoints.impl;

import io.training.boundary.PostService;
import io.training.control.rest.endPoints.PostRESTEndpoint;
import io.training.entity.Post;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class PostRESTEndpointImpl implements PostRESTEndpoint {
  @EJB
  private PostService postService;

  @Override
  public Response listAllPosts(long userId, String title, int start, int limit) {
    List<Post> postList = postService.findRange(new int[]{start,limit});;
    if(userId != 0.0f){
      return Response.ok().entity(postList
              .stream()
              .filter((post) -> post.getUser().getId()==(userId)).collect(Collectors.toList()))
              .build();
    }
    if(title != null){
      return Response.ok().entity(postList
              .stream()
              .filter((post) -> post.getTitle().equals(title)).collect(Collectors.toList()))
              .build();
    }
    return postList.size() > 0 ? Response.ok(postService.findAll()).build()
            : Response.status(Response.Status.NO_CONTENT).build();

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

