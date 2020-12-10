package io.training.control.rest.endPoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.entity.User;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/users")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "User")
public interface UserRESTServerEndpoint {
    @GET
    @Path("/{id}")
    @Operation(
            summary = "Get user by  id",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    Response retrieveUser(@PathParam("id") long id);

    @GET
    @Operation(
            summary = "Get all Users",
            responses = {
                    @ApiResponse(
                            description = "List containing all the User",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = User.class
                                            )
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "User not found")
            })
    Response getAllUsers(@QueryParam("phone") String phone,
                         @QueryParam("username") String username,
                         @QueryParam("email") String email,
                         @QueryParam("start") @DefaultValue("1") int start,
                         @QueryParam("limit") @DefaultValue("2") int limit);

    @POST
    @Operation(
            summary = "Create user",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request")
            }
    )
    Response createUser(@Valid User user,
                        @Context UriInfo uriInfo);

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Edit user",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Error"),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "User already exists"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    Response updateUser(@PathParam("id") long id, @Valid User user,
                        @Context UriInfo uriInfo);

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete user by  id",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "User not found")
            })
    Response deleteUser(@PathParam("id") long id);
}
