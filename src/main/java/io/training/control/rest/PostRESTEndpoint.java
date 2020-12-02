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

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;


@Path("/posts")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Post")
public interface PostRESTEndpoint {
  @GET
  @Path("/{id}")
  @Operation(
          summary = "Get post by  id",
          responses = {
                  @ApiResponse(
                          description = "The Post",
                          content =
                          @Content(
                                  mediaType = "application/json",
                                  schema = @Schema(implementation = Post.class))),
                  @ApiResponse(responseCode = "400", description = "Post not found", content =
                  @Content(
                          mediaType = "application/json",
                          schema = @Schema(implementation = Error.class)))
          })
  Response retrievePost(@PathParam("id") int id);

  @POST
    Response createPost(Post post);
}
