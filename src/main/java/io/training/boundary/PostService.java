package io.training.boundary;


import io.training.entity.Post;

import java.util.List;

public interface PostService extends CrudAbstractBean<Post,Integer> {
    List<Post> getPostByTitle(String title);
    List<Post> getPostByUserId(long userId);
}
