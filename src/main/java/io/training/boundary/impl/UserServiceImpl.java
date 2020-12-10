package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.training.boundary.UserService;
import io.training.util.DeleteStatus;
import io.training.entity.User;
import io.training.util.Constants;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Stateless
public class UserServiceImpl extends CrudAbstractBeanImpl<User, Long> implements UserService {
  @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
  private EntityManager entityManager;
  public UserServiceImpl() {
    super(User.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }

  @Override
  public boolean delete(User entity) {
    entity.setDeleteStatus(DeleteStatus.DELETED);
    User edit= update(entity);
    return edit != null;
  }

}
