package io.training.boundary;

import io.training.entity.Comment;

import java.util.List;

public interface CommentService extends CrudAbstractBean<Comment,Integer> {
    List<Comment> getCommentByNameOrEmail(String title);
    List<Comment> getCommentByPostId(int userId);
}
