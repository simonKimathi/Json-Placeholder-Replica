package io.training.boundary.impl;

import io.training.boundary.ToDosService;
import io.training.entity.*;
import io.training.util.DeleteStatus;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Stateless
public class ToDosServiceImpl extends CrudAbstractBeanImpl<ToDo, Long> implements ToDosService {

    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    public ToDosServiceImpl() {
        super(ToDo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<ToDo> getToDosByUserId(long id) {
        TypedQuery<ToDo> postTypedQuery = getEntityManager()
                .createQuery("select t from ToDos t where t.user.id =: id and t.deleteStatus =: deleteStatus", ToDo.class)
                .setParameter("id", id)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<ToDo> postList = postTypedQuery.getResultList();
        if(postList.size()>0){
            return postList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ToDo> getToDosByTitle(String title) {
        TypedQuery<ToDo> postTypedQuery = getEntityManager()
                .createQuery("select t from ToDos t where t.title LIKE : title and t.deleteStatus =: deleteStatus", ToDo.class)
                .setParameter("title", "%"+title+"%")
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<ToDo> postList = postTypedQuery.getResultList();
        if(postList.size()>0){
            return postList;
        }
        return Collections.emptyList();
    }

    @Override
    public boolean delete(ToDo entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        ToDo edit= update(entity);
        return edit != null;
    }

    @Override
    public ToDo find(Long id) {
        List<ToDo> resultList = getEntityManager().createQuery("select t from ToDos t where t.deleteStatus =: deleteStatus and t.id =: id ", ToDo.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE)
                .setParameter("id",id)
                .getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList.get(0);
        }
        return null;
    }

    @Override
    public List<ToDo> findAll() {
        List<ToDo> resultList = getEntityManager().createQuery("select t from ToDos t where t.deleteStatus =: deleteStatus", ToDo.class)
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE).getResultList();
        if (resultList.size()>0 && resultList != null){
            return resultList;
        }
        return Collections.emptyList();
    }
}
