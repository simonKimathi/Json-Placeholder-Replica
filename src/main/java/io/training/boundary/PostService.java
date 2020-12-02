package io.training.boundary;


import io.training.entity.Post;
import io.training.entity.User;

import java.util.List;
import java.util.Optional;

public interface PostService extends AbstractBean<Post,Integer> {
    List<Post> getPostByTitle(String title);
    List<Post> getPostByUserId(long userId);
}
