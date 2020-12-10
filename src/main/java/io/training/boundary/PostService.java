package io.training.boundary;


import io.training.entity.Post;

import java.util.List;

public interface PostService extends CrudAbstractBean<Post,Long> {

    List<Post> findAllByUserId(long userId);
}
