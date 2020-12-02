package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import io.training.boundary.PostService;
import io.training.entity.Post;
import io.training.util.Constants;

@Stateless
public class PostServiceImpl extends AbstractBeanImpl<Post,Integer> implements PostService {
  @PersistenceContext(name = Constants.ENTITY_MANAGER_NAME)
  private EntityManager entityManager;
  public PostServiceImpl() {
    super(Post.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }


}
