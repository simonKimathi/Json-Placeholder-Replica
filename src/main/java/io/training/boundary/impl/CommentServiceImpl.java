package io.training.boundary.impl;

import io.training.boundary.CommentService;
import io.training.entity.Comment;
import io.training.util.Constants;
import io.training.util.DeleteStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;


@Stateless
public class CommentServiceImpl extends CrudAbstractBeanImpl<Comment,Long> implements CommentService {
    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public CommentServiceImpl() {
        super(Comment.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public boolean delete(Comment entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        Comment edit= update(entity);
        return edit != null;
    }

    @Override
    public List<Comment> findAllByPostId(long postId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Comment> query = criteriaBuilder.createQuery(Comment.class);
        Root<Comment> from = query.from(Comment.class);
        query.select(from)
                .where(
                        criteriaBuilder.equal(
                                from.get("post").get("id"),
                                postId
                        )
                );
        return getEntityManager().createQuery(query).getResultList();
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

}
