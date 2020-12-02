package io.training.control.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.control.rest.impl.Error;
import io.training.entity.Post;
import io.training.entity.Post;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/posts")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Post")
public interface PostRESTEndpoint {
    @GET
    @Path("/getPostById/{id}")
    @Operation(
            summary = "Get Post by  id",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found")
            })
    Response retrievePost(@PathParam("id") int id);

    @GET
    @Path("/getAllPosts")
    @Operation(
            summary = "Get all Posts",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found")
            })
    Response getAllPosts();

    @GET
    @Path("/getPostByUserId/{userId}")
    @Operation(
            summary = "Get Post by  User Id",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found")
            })
    Response getPostByUserId(@PathParam("userId") long userId);

    @GET
    @Path("/getPostByTitle")
    @Operation(
            summary = "Get Post by  Title",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found")
            })
    Response getPostByTitle(@QueryParam("title") String title);


    @POST
    @Operation(
            summary = "Create Post",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response createPost(Post post);

    @PUT
    @Path("/editPost")
    @Operation(
            summary = "Edit Post",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response editPost(Post post);

    @DELETE
    @Path("/deletePostById/{id}")
    @Operation(
            summary = "Delete Post by  id",
            responses = {
                    @ApiResponse(
                            description = "The Post",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Post.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found")
            })
    Response deletePost(@PathParam("id") int id);
}
