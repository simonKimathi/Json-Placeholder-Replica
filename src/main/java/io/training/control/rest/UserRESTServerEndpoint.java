package io.training.control.rest;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.entity.User;

@Path("/users")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "User")
public interface UserRESTServerEndpoint {
    @GET
    @Path("/getUserById/{id}")
    @Operation(
            summary = "Get user by  id",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "User not found")
            })
    Response retrieveUser(@PathParam("id") long id);

    @GET
    @Path("/getAllUsers")
    @Operation(
            summary = "Get all Users",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "User not found")
            })
    Response getAllUsers();

    @GET
    @Path("/getUserByEmail/{email}")
    @Operation(
            summary = "Get user by  email",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "User not found")
            })
    Response getUserByEmail(@PathParam("email") String email);

    @GET
    @Path("/getUserByUsername")
    @Operation(
            summary = "Get user by  username",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "User not found")
            })
    Response getUserByUsername(@QueryParam("username") String username);


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
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response createUser(User user);

    @PUT
    @Path("/editUser")
    @Operation(
            summary = "Edit user",
            responses = {
                    @ApiResponse(
                            description = "The User",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response editUser(User user);

    @DELETE
    @Path("/deleteUserById/{id}")
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
