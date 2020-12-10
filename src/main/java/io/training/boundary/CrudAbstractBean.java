package io.training.boundary;

import java.util.List;
import java.util.Optional;

public interface CrudAbstractBean<T,ID> {
  T save(T entity);

  List<T> saveAll(List<T> entities);

  T update(T entity);

  List<T> updateAll(List<T> entities);

  boolean delete(T entity);

  void deleteAll();

  void deleteAll(List<T> entities);

  void deleteById(ID id);

  boolean existsById(ID id);

  T find(ID primaryKey);

  List<T> findAll();

  List<T> findAllById(List<ID> ids);

  Optional<T> findById(ID id);

  List<T> findRange(int[] range);

  long count();
}
