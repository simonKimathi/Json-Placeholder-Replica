package io.training.control.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.entity.ToDo;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/todos")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
@Tag(name = "Todos")
public interface ToDosRESTEndpoint {
    @GET
    @Path("/{id}")
    @Operation(
            summary = "Get ToDos by  id",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response retrieveToDos(@PathParam("id") long id);

    @GET
    @Operation(
            summary = "Get all ToDoss",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response getAllToDoss();

    @GET
    @Path("/getToDosByUserId/{id}")
    @Operation(
            summary = "Get ToDos by  user id",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response getToDosByUserId(@PathParam("id") long id);

    @GET
    @Path("/getToDosByTitle")
    @Operation(
            summary = "Get ToDos by  Title",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response getToDosByTitle(@QueryParam("title") String title);


    @POST
    @Operation(
            summary = "Create ToDos",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response createToDos(ToDo toDo);

    @PUT
    @Path("/{id}")
    @Operation(
            summary = "Edit ToDos",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response editToDos(@PathParam("id") long id, ToDo toDo);

    @DELETE
    @Path("/{id}")
    @Operation(
            summary = "Delete ToDos by  id",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ToDo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response deleteToDos(@PathParam("id") long id);
}
