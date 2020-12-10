package io.training.control.rest.endPoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.control.rest.endPoints.impl.Error;
import io.training.entity.Comment;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

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
        Response getCommentById(@PathParam("id") long id);

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
        Response getAllComments(@QueryParam("postId") long postId,
                                @QueryParam("nameOrEmail") String nameOrEmail);



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
        Response createComment(@Valid Comment comment,
                               @Context UriInfo uriInfo);

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
        Response editComment(@PathParam("id") long id ,@Valid Comment comment,
                             @Context UriInfo uriInfo);

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
        Response deleteComment(@PathParam("id") long id);
}

