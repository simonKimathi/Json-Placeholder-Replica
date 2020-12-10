package io.training.boundary.impl;

import io.training.boundary.PhotoEndpoint;
import io.training.boundary.PhotoService;
import io.training.entity.Photo;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.Valid;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Optional;

@Stateless
public class PhotoEndpointImpl implements PhotoEndpoint {
    @EJB
    private PhotoService photoService;
    @Override
    public Response listAllPhotos(long albumId) {
        return albumId > 0 ? Response.ok(photoService.findAllByAlbumId(albumId)).build()
                : Response.ok(photoService.findAll()).build();
    }

    @Override
    public Response createPhoto(Photo photo, UriInfo uriInfo) {
        if (photoService.existsById(photo.getId())) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        Photo savedPhoto = photoService.save(photo);
        URI location = uriInfo.getBaseUriBuilder()
                .path(PhotoEndpoint.class)
                .path(String.valueOf(savedPhoto.getId()))
                .build();
        return Response.created(location).entity(savedPhoto).build();
    }


    @Override
    public Response getPhotoById(long id) {
        Optional<Photo> optionalPhoto = photoService.findById(id);
        return optionalPhoto.isPresent() ?
                Response.ok().entity(optionalPhoto.get()).build()
                :
                Response.status(Response.Status.NOT_FOUND).build();
    }

    @Override
    public Response updatePhoto(long id, @Valid Photo photo, UriInfo uriInfo) {
        if (!photoService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        Photo updatedPhoto = photoService.update(photo);
        URI location = uriInfo.getBaseUriBuilder()
                .path(PhotoEndpoint.class)
                .path(String.valueOf(updatedPhoto.getId()))
                .build();
        return Response.created(location).entity(updatedPhoto).build();
    }

    @Override
    public Response deletePhoto(long id) {
        if (!photoService.existsById(id)) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        photoService.deleteById(id);
        return Response.ok().build();
    }
}
