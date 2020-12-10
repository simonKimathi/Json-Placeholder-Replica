package io.training.boundary.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import io.training.boundary.ProjectService;
import io.training.entity.Project;
import io.training.util.Constants;

@Stateless
public class ProjectTaskServiceImpl extends CrudAbstractBeanImpl<Project, Long> implements ProjectService {

  @PersistenceContext(name = Constants.ENTITY_MANAGER_NAME)
  private EntityManager entityManager;

  public ProjectTaskServiceImpl() {
    super(Project.class);
  }

  @Override
  protected EntityManager getEntityManager() {
    return entityManager;
  }
}