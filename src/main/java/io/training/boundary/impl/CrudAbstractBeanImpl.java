package io.training.boundary.impl;

import io.training.boundary.CrudAbstractBean;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class CrudAbstractBeanImpl<T, ID> implements CrudAbstractBean<T, ID> {
  private final Class<T> entityClass;

  protected abstract EntityManager getEntityManager();

  public CrudAbstractBeanImpl(Class<T> entityClass) {
    this.entityClass = entityClass;
  }

  @Override
  public T save(T entity) {
    getEntityManager().persist(entity);
    return entity;
  }

  @Override
  public List<T> saveAll(List<T> entities) {
    List<T> savedEntities=new ArrayList<>();
    for (T entity: entities){
      savedEntities.add(save(entity));
    }
    return savedEntities;
  }

  @Override
  public T update(T entity) {
    getEntityManager().merge(entity);
    return entity;
  }

  @Override
  public List<T> updateAll(List<T> entities) {
    List<T> updatedEntities=new ArrayList<>();
    for(T entity: entities){
      updatedEntities.add(update(entity));
    }
    return updatedEntities;
  }

  @Override
  public boolean delete(T entity) {
    getEntityManager().remove(getEntityManager().merge(entity));
    return true;
  }

  @Override
  public void deleteAll() {
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(entityClass);
    Root<T> from = criteriaQuery.from(entityClass);
    criteriaQuery.select(from);
    TypedQuery<T> query = getEntityManager().createQuery(criteriaQuery);
    List<T> resultList = query.getResultList();
    for (T entity : resultList) {
      delete(entity);
    }
  }

  @Override
  public void deleteAll(List<T> entities) {
    for (T entity : entities){
      delete(entity);
    }

  }

  @Override
  public void deleteById(ID id) {
    Optional<T> optionalT = findById(id);
    optionalT.ifPresent(this::delete);
  }

  @Override
  public T find(ID id) {
    return getEntityManager().find(entityClass, id);
  }


  @Override
  public boolean existsById(ID id) {
    Optional<T> optionalT = findById(id);
    return optionalT.isPresent();
  }

  @Override
  public List<T> findAllById(List<ID> ids) {
    return null;
  }

  @Override
  public Optional<T> findById(ID id) {
    T t = getEntityManager().find(entityClass, id);
    if (t != null) {
      return Optional.of(t);
    }
    return Optional.empty();
  }

  @Override
  public List<T> findAll() {
    /*CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    List<T> entityLists = getEntityManager().createQuery(cq).getResultList();
    if(entityLists.size()>0){
      return entityLists;
    }
    else
      return Collections.emptyList();*/
    CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
    CriteriaQuery<T> cq = criteriaBuilder.createQuery(entityClass);
    Root<T> from = cq.from(entityClass);
    cq.select(from);
    return getEntityManager().createQuery(cq).getResultList();
  }

  @Override
  public List<T> findRange(int[] range) {
    CriteriaQuery<T> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    TypedQuery<T> q = getEntityManager().createQuery(cq);
    q.setFirstResult(range[0]);//set offset
    q.setMaxResults(range[1] - range[0]);//limit
    return q.getResultList();
  }

  @Override
  public long count() {
    CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root<T> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    TypedQuery<Long> q = getEntityManager().createQuery(cq);
    return q.getSingleResult();
  }
}
