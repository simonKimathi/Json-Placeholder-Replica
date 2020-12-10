package io.training.control.rest.endPoints;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.training.entity.Photo;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("/photos")
@Tag(name = "photos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public interface PhotoRestEndpoint {
    @Operation(
            summary = "Get all photos",
            responses = {
                    @ApiResponse(
                            description = "List containing all photos",
                            content = @Content(
                                    array = @ArraySchema(
                                            schema = @Schema(
                                                    implementation = Photo.class
                                            )
                                    )
                            )
                    )

            })
    @GET
    Response listAllPhotos(@QueryParam("albumId") long albumId,
                           @QueryParam("title") String title,
                           @QueryParam("start") @DefaultValue("1") int start,
                           @QueryParam("limit") @DefaultValue("2") int limit);


    @Operation(
            summary = "Create photo",
            responses = {
                    @ApiResponse(
                            description = "The created photo",
                            responseCode = "201",
                            content =
                            @Content(
                                    schema = @Schema(
                                            implementation = Photo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    )


            }
    )
    @POST
    Response createPhoto(@Valid Photo photo,
                        @Context UriInfo uriInfo);

    @GET
    @Path("/{id}")
    @Operation(
            summary = "Get photo by  id",
            responses = {
                    @ApiResponse(
                            description = "The photo with id",
                            responseCode = "200",
                            content =
                            @Content(
                                    schema = @Schema(
                                            implementation = Photo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "photo not found"
                    )
            })
    Response getPhotoById(@PathParam("id") long id);

    @Operation(
            summary = "Update photo",
            responses = {
                    @ApiResponse(
                            description = "The updated photo",
                            responseCode = "200",
                            content =
                            @Content(
                                    schema = @Schema(
                                            implementation = Photo.class
                                    )
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "photo already exists"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @PUT
    @Path("{id}")
    Response updatePhoto(@PathParam("id") long id, @Valid Photo photo,
                        @Context UriInfo uriInfo);

    @Operation(
            summary = "delete photo",
            responses = {
                    @ApiResponse(
                            description = "successful deletion of photo",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Not Found"
                    )
            }
    )
    @DELETE
    @Path("{id}")
    Response deletePhoto(@PathParam("id") long id);



}
