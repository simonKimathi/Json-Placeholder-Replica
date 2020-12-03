package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import io.training.boundary.PostService;
import io.training.entity.DeleteStatus;
import io.training.entity.Post;
import io.training.entity.User;
import io.training.util.Constants;

import java.util.Collections;
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
            .createQuery("select p from Post p where p.title LIKE : title and p.deleteStatus =: deleteStatus", Post.class)
            .setParameter("title", "%"+title+"%")
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
    List<Post> postList = postTypedQuery.getResultList();
    if(postList.size()>0){
      return postList;
    }
      return null;
  }

  @Override
  public List<Post> getPostByUserId(long userId) {
    TypedQuery<Post> postTypedQuery = getEntityManager()
            .createQuery("select p from Post p where p.user.id =: userId and p.deleteStatus =: deleteStatus", Post.class)
            .setParameter("userId", userId)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
    List<Post> postList = postTypedQuery.getResultList();
    if(postList.size()>0){
      return postList;
    }
    return null;
  }

  @Override
  public boolean remove(Post entity) {
    entity.setDeleteStatus(DeleteStatus.DELETED);
    Post edit= edit(entity);
    return edit != null;
  }

  @Override
  public Post find(Integer id) {
    List<Post> resultList = getEntityManager().createQuery("select p from Post p where p.deleteStatus =: deleteStatus and p.id =: id ",Post.class)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE)
            .setParameter("id",id)
            .getResultList();
    if (resultList.size()>0 && resultList != null){
      return resultList.get(0);
    }
    return null;
  }

  @Override
  public List<Post> findAll() {
    List<Post> resultList = getEntityManager().createQuery("select p from Post p where p.deleteStatus =: deleteStatus",Post.class)
            .setParameter("deleteStatus", DeleteStatus.AVAILABLE).getResultList();
    if (resultList.size()>0 && resultList != null){
      return resultList;
    }
    return Collections.emptyList();
  }
}
