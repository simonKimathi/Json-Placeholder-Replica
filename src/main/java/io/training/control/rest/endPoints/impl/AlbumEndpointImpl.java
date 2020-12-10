package io.training.control.rest.endPoints.impl;

import io.training.boundary.AlbumService;
import io.training.control.rest.endPoints.AlbumEndpoint;
import io.training.entity.Album;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Stateless
public class AlbumEndpointImpl implements AlbumEndpoint {
    @EJB
    private AlbumService albumService;

    @Override
    public Response listAllAlbums(long userId, String title) {
        List<Album> albumList =albumService.findAll();
        if(title != null){
            return Response.ok()
                    .entity(albumList
                    .stream()
                    .filter(album -> album.getTitle().equals(title))
                    .collect(Collectors.toList()))
                    .build();
        }


        return userId > 0 ? Response.ok(albumService.findAllByUserId(userId)).build()
                : Response.ok().entity(albumList).build();
    }

    @Override
    public Response createAlbum(@Valid Album album, UriInfo uriInfo) {
        if (albumService.existsById(album.getId())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        Album savedAlbum = albumService.save(album);
        URI location = uriInfo.getBaseUriBuilder()
                .path(AlbumEndpoint.class)
                .path(String.valueOf(savedAlbum.getId()))
                .build();
        return Response.created(location).entity(savedAlbum).build();
    }

    @Override
    public Response getAlbumById(long id) {
        Optional<Album> optionalAlbum = albumService.findById(id);
        return optionalAlbum.isPresent() ?
                Response.ok().entity(optionalAlbum.get()).build()
                :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response updateAlbum(long id, @Valid Album album, UriInfo uriInfo) {
        if (!albumService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Album updateAlbum = albumService.update(album);
        URI location = uriInfo.getBaseUriBuilder()
                .path(AlbumEndpoint.class)
                .path(String.valueOf(updateAlbum.getId()))
                .build();
        return Response.created(location).entity(updateAlbum).build();
    }

    @Override
    public Response deleteAlbum(long id) {
        if (!albumService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        albumService.deleteById(id);
        return Response.ok().build();
    }
}
