package io.training.boundary.impl;

import io.training.boundary.CommentService;
import io.training.entity.Comment;
import io.training.entity.commonClasses.DeleteStatus;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;


@Stateless
public class CommentServiceImpl extends CrudAbstractBeanImpl<Comment,Integer> implements CommentService {
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
                .createQuery("select c from Comment c where c.name LIKE : nameOrEmail or c.email LIKE : nameOrEmail and c.deleteStatus =: deleteStatus", Comment.class)
                .setParameter("nameOrEmail", "%"+nameOrEmail+"%")
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<Comment> commentList = commentTypedQuery.getResultList();
        if(commentList.size()>0){
            return commentList;
        }
        return null;
    }

    @Override
    public List<Comment> getCommentByPostId(int userId) {
        TypedQuery<Comment> commentTypedQuery = getEntityManager()
                .createQuery("select c from Comment c where c.post.id =: userId and c.deleteStatus =: deleteStatus", Comment.class)
                .setParameter("userId", userId)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<Comment> commentList = commentTypedQuery.getResultList();
        if(commentList.size()>0){
            return commentList;
        }
        return null;
    }


    @Override
    public boolean delete(Comment entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        Comment edit= update(entity);
        return edit != null;
    }

    @Override
    public Comment find(Integer id) {
        List<Comment> resultList = getEntityManager().createQuery("select c from Comment c where c.deleteStatus =: deleteStatus and c.id =: id ",Comment.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE)
                .setParameter("id",id)
                .getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> resultList = getEntityManager().createQuery("select c from Comment c where c.deleteStatus =: deleteStatus",Comment.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE).getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList;
        }
        return Collections.emptyList();
    }
}
