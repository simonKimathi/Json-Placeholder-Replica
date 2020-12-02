package io.training.boundary;

import io.training.entity.Comment;
import io.training.entity.Post;

import java.util.List;

public interface CommentService extends AbstractBean<Comment,Integer>  {
    List<Comment> getCommentByNameOrEmail(String title);
    List<Comment> getCommentByPostId(int userId);
}
