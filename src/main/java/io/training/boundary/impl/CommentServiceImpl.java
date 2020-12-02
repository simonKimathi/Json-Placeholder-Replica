package io.training.boundary.impl;

import io.training.boundary.CommentService;
import io.training.entity.Comment;
import io.training.entity.Post;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class CommentServiceImpl extends AbstractBeanImpl<Comment,Integer> implements CommentService {
    @PersistenceContext(name = Constants.ENTITY_MANAGER_NAME)
    private EntityManager entityManager;

    public CommentServiceImpl() {
        super(Comment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}
