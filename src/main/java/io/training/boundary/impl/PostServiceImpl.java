package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.training.boundary.PostService;
import io.training.entity.Post;
import io.training.entity.User;
import io.training.util.Constants;

import java.util.List;
import java.util.Optional;

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

  @Override
  public List<Post> getPostByTitle(String title) {
    TypedQuery<Post> postTypedQuery = getEntityManager()
            .createQuery("select p from Post p where p.title =: title", Post.class)
            .setParameter("title", title);
    List<Post> postList = postTypedQuery.getResultList();
    if(postList.size()>0){
      return postList;
    }
      return null;
  }

  @Override
  public List<Post> getPostByUserId(long userId) {
    TypedQuery<Post> postTypedQuery = getEntityManager()
            .createQuery("select p from Post p where p.user.id =: userId", Post.class)
            .setParameter("userId", userId);
    List<Post> postList = postTypedQuery.getResultList();
    if(postList.size()>0){
      return postList;
    }
    return null;
  }
}
