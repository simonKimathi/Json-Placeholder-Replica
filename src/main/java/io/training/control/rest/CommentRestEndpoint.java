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
            summary = "Get comment by  id",
            responses = {
                    @ApiResponse(
                            description = "The Comment",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Comment.class))),
                    @ApiResponse(responseCode = "400", description = "Post not found", content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = Error.class)))
            })
    Response retrieveComment(@PathParam("id") int id);

    @POST
    Response createComment(Comment post);
}
