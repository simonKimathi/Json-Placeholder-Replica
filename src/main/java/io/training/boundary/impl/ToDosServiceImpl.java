package io.training.boundary.impl;

import io.training.boundary.ToDosService;
import io.training.entity.*;
import io.training.util.DeleteStatus;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class ToDosServiceImpl extends CrudAbstractBeanImpl<Todo, Long> implements ToDosService {

    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    public ToDosServiceImpl() {
        super(Todo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }



    @Override
    public boolean delete(Todo entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        Todo edit= update(entity);
        return edit != null;
    }

    @Override
    public List<Todo> findAllByUserId(long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Todo> query = criteriaBuilder.createQuery(Todo.class);
        Root<Todo> from = query.from(Todo.class);
        query.select(from)
                .where(
                        criteriaBuilder.equal(
                                from.get("user").get("id"),
                                userId
                        )
                );
        return getEntityManager().createQuery(query).getResultList();
    }


}
