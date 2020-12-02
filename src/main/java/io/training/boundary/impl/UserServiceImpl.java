package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import io.training.boundary.UserService;
import io.training.entity.User;
import io.training.util.Constants;
@Stateless
public class UserServiceImpl extends AbstractBeanImpl<User, Long> implements UserService {
  @PersistenceContext(name = Constants.ENTITY_MANAGER_NAME)
  private EntityManager entityManager;
  public UserServiceImpl() {
    super(User.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }
}
