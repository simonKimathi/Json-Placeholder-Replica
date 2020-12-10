package io.training.boundary;



import io.training.entity.Album;

import java.util.List;

public interface AlbumService extends CrudAbstractBean<Album,Long>{
    List<Album> findAllByUserId(long userId);
}
