package io.training.control.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.control.rest.impl.Error;
import io.training.entity.Comment;
import io.training.entity.Post;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/comments")

@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Comment")
public interface CommentRestEndpoint {

        @GET
        @Path("/{id}")
        @Operation(
                summary = "Get Comment by  id",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Comment not found")
                })
        Response getCommentById(@PathParam("id") int id);

        @GET
        @Operation(
                summary = "Get all Comments",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Comment not found")
                })
        Response getAllComments();

        @GET
        @Path("/getCommentByNameOrEmail/{param}")
        @Operation(
                summary = "Get Comment by  User Id",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Comment not found")
                })
        Response getCommentByNameOrEmail(@PathParam("param") String param);

        @GET
        @Path("/getCommentByPostId")
        @Operation(
                summary = "Get Comment by  Post Id",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Comment not found")
                })
        Response getCommentByPostId(@QueryParam("Id") int Id);


        @POST
        @Operation(
                summary = "Create Comment",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Error")
                })
        Response createComment(Comment comment);

        @PUT
        @Path("/{id}")
        @Operation(
                summary = "Edit Comment",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Error")
                })
        Response editComment(@PathParam("id") int id , Comment comment);

        @DELETE
        @Path("/{id}")
        @Operation(
                summary = "Delete Comment by  id",
                responses = {
                        @ApiResponse(
                                description = "The Comment",
                                content =
                                @Content(
                                        mediaType = "application/json",
                                        schema = @Schema(implementation = Comment.class))),
                        @ApiResponse(responseCode = "400", description = "Comment not found")
                })
        Response deleteComment(@PathParam("id") int id);
}

