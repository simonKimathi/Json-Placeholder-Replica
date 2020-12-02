package io.training.boundary.impl;

import io.training.boundary.CommentService;
import io.training.entity.Comment;
import io.training.entity.Post;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


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

    @Override
    public List<Comment> getCommentByNameOrEmail(String nameOrEmail) {
        TypedQuery<Comment> commentTypedQuery = getEntityManager()
                .createQuery("select c from Comment c where c.name LIKE : nameOrEmail or c.email LIKE : nameOrEmail", Comment.class)
                .setParameter("nameOrEmail", "%"+nameOrEmail+"%");
        List<Comment> commentList = commentTypedQuery.getResultList();
        if(commentList.size()>0){
            return commentList;
        }
        return null;
    }

    @Override
    public List<Comment> getCommentByPostId(int userId) {
        TypedQuery<Comment> commentTypedQuery = getEntityManager()
                .createQuery("select c from Comment c where c.post.id =: userId", Comment.class)
                .setParameter("userId", userId);
        List<Comment> commentList = commentTypedQuery.getResultList();
        if(commentList.size()>0){
            return commentList;
        }
        return null;
    }
}
