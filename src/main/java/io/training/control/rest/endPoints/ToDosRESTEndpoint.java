package io.training.control.rest.endPoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.entity.Todo;

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
                                    schema = @Schema(implementation = Todo.class))),
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
                                    schema = @Schema(implementation = Todo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response getAllToDoss(
            @QueryParam("start") @DefaultValue("0") int start,
            @QueryParam("limit") @DefaultValue("2") int limit);


    @POST
    @Operation(
            summary = "Create ToDos",
            responses = {
                    @ApiResponse(
                            description = "The ToDos",
                            content =
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = Todo.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response createToDos(Todo toDo);

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
                                    schema = @Schema(implementation = Todo.class))),
                    @ApiResponse(responseCode = "400", description = "Error")
            })
    Response editToDos(@PathParam("id") long id, Todo toDo);

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
                                    schema = @Schema(implementation = Todo.class))),
                    @ApiResponse(responseCode = "400", description = "ToDos not found")
            })
    Response deleteToDos(@PathParam("id") long id);
}
