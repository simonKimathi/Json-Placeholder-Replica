package io.training.boundary.impl;

import io.training.boundary.ToDosService;
import io.training.entity.Comment;
import io.training.entity.DeleteStatus;
import io.training.entity.ToDos;
import io.training.entity.User;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
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

    @Override
    public boolean remove(ToDos entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        ToDos edit= edit(entity);
        return edit != null;
    }

    @Override
    public ToDos find(Long id) {
        List<ToDos> resultList = getEntityManager().createQuery("select t from ToDos t where t.deleteStatus =: deleteStatus and t.id =: id ",ToDos.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE)
                .setParameter("id",id)
                .getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<ToDos> findAll() {
        List<ToDos> resultList = getEntityManager().createQuery("select t from ToDos t where t.deleteStatus =: deleteStatus",ToDos.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE).getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList;
        }
        return Collections.emptyList();
    }
}
