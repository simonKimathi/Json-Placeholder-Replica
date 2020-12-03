package io.training.boundary.impl;

import io.training.boundary.ToDosService;
import io.training.entity.ToDos;
import io.training.entity.User;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ToDosServiceImpl extends AbstractBeanImpl<ToDos, Long> implements ToDosService {

    @PersistenceContext(name = Constants.ENTITY_MANAGER_NAME)
    private EntityManager entityManager;
    public ToDosServiceImpl() {
        super(ToDos.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<ToDos> getToDosByUserId(long id) {
        return null;
    }

    @Override
    public List<ToDos> getToDosByTitle(String title) {
        return null;
    }
}
