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
  public Optional<User> getUserByEmail(String email) {
    TypedQuery<User> userTypedQuery =getEntityManager()
            .createQuery("select u from User u where u.email =: email and u.deleteStatus =: deleteStatus",User.class)
            .setParameter("email",email)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
    List<User> userList=userTypedQuery.getResultList();
    if(userList.size()>0){
      return Optional.of(userList.get(0));
    }
      return Optional.empty();
  }

  @Override
  public Optional<User> getUserByUsername(String username) {
    TypedQuery<User> userTypedQuery =getEntityManager()
            .createQuery("select u from User u where u.username= : username and u.deleteStatus =: deleteStatus",User.class)
            .setParameter("username",username)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
    List<User> userList=userTypedQuery.getResultList();
    if(userList.size()>0){
      return Optional.of(userList.get(0));
    }
    return Optional.empty();
  }

  @Override
  public boolean delete(User entity) {
    entity.setDeleteStatus(DeleteStatus.DELETED);
    User edit= update(entity);
    return edit != null;
  }

  @Override
  public User find(Long id) {
    List<User> resultList = getEntityManager().createQuery("select u from User u where u.deleteStatus =: deleteStatus and u.id =: id ",User.class)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE)
            .setParameter("id",id)
            .getResultList();
    if (resultList.size()>0 && resultList != null){
      return resultList.get(0);
    }
    return null;
  }

  @Override
  public List<User> findAll() {
    List<User> resultList = getEntityManager().createQuery("select u from User u where u.deleteStatus =: deleteStatus",User.class)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE).getResultList();
    if (resultList.size()>0 && resultList != null){
      return resultList;
    }
    return Collections.emptyList();
  }
}
