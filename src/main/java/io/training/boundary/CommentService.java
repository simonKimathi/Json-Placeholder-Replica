package io.training.boundary;

import io.training.entity.Comment;

import java.util.List;

public interface CommentService extends CrudAbstractBean<Comment,Integer> {
    public List<Comment> findAllByPostId(long postId);
}
