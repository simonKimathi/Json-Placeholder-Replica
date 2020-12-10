package io.training.boundary;


import io.training.entity.Photo;

import java.util.List;

public interface PhotoService extends CrudAbstractBean<Photo,Long> {
    List<Photo> findAllByAlbumId(long albumId);
}
