package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import io.training.boundary.PostService;
import io.training.entity.Commons.DeleteStatus;
import io.training.entity.Post;
import io.training.util.Constants;

import java.util.List;

@Stateless
public class PostServiceImpl extends CrudAbstractBeanImpl<Post,Long> implements PostService {
  @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
  private EntityManager entityManager;
  public PostServiceImpl() {
    super(Post.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }



  @Override
  public boolean delete(Post entity) {
    entity.setDeleteStatus(DeleteStatus.DELETED);
    Post edit= update(entity);
    return edit != null;
  }

  @Override
  public List<Post> findAllByUserId(long userId) {
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<Post> query = criteriaBuilder.createQuery(Post.class);
    Root<Post> from = query.from(Post.class);
    query.select(from)
            .where(
                    criteriaBuilder.equal(
                            from.get("user").get("id"),
                            userId
                    )
            );
    return getEntityManager().createQuery(query).getResultList();


  }

}
