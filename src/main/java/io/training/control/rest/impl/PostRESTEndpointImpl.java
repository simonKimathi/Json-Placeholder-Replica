package io.training.control.rest.impl;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.POST;
import javax.ws.rs.core.Response;
import io.training.boundary.PostService;
import io.training.control.rest.PostRESTEndpoint;
import io.training.entity.Post;
import io.training.entity.User;

import java.util.List;
import java.util.Optional;

@Stateless
public class PostRESTEndpointImpl implements PostRESTEndpoint {
  @EJB
  private PostService postService;

  @Override
  public Response getPostById(int id) {
    Post post = postService.find(id);
    if(post==null){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    return Response.ok().entity(post).build();
  }

  @Override
  public Response getAllPosts() {
    List<Post> postList = postService.findAll();
    if(postList.size()>0){
      return Response.ok().entity(postList).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response getPostByUserId(long userId) {
    List<Post> userList = postService.getPostByUserId(userId);
    if(userList != null){
      return Response.ok().entity(userList).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response getPostByTitle(String title) {
    List<Post> userList = postService.getPostByTitle(title);
    if(userList != null){
      return Response.ok().entity(userList).build();
    }
    return Response.status(Response.Status.NOT_FOUND).build();
  }

  @Override
  public Response createPost(Post post) {
    Post createdPost = postService.create(post);
    if (createdPost==null){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().entity(createdPost).build();
  }

  @Override
  public Response editPost(int id, Post post) {
    if(id == post.getId()){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    Post FindPost = postService.find(id);
    if(FindPost == null){
      return Response.status(Response.Status.NOT_FOUND).build();
    }
    Post editedPost = postService.edit(post);
    return Response.ok().entity(editedPost).build();
  }

  @Override
  public Response deletePost(int id) {
    Post deletedPost = postService.find(id);
    if (deletedPost==null){
      return Response.status(Response.Status.BAD_REQUEST).build();
    }
    return Response.ok().entity(postService.remove(deletedPost)).build();
  }
}
