package io.training.boundary;



import io.training.entity.Album;

import java.util.List;

public interface AlbumRepository extends CrudAbstractBean<Album,Long>{
    List<Album> findAllByUserId(long userId);
}
