package io.training.boundary.impl;

import io.training.boundary.ToDosService;
import io.training.entity.*;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        TypedQuery<ToDos> postTypedQuery = getEntityManager()
                .createQuery("select t from ToDos t where t.user.id =: id and t.deleteStatus =: deleteStatus", ToDos.class)
                .setParameter("id", "%"+id+"%")
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<ToDos> postList = postTypedQuery.getResultList();
        if(postList.size()>0){
            return postList;
        }
        return Collections.emptyList();
    }

    @Override
    public List<ToDos> getToDosByTitle(String title) {
        TypedQuery<ToDos> postTypedQuery = getEntityManager()
                .createQuery("select t from ToDos t where t.title LIKE : title and t.deleteStatus =: deleteStatus", ToDos.class)
                .setParameter("title", "%"+title+"%")
                .setParameter("deleteStatus", DeleteStatus.AVAILABLE);
        List<ToDos> postList = postTypedQuery.getResultList();
        if(postList.size()>0){
            return postList;
        }
        return Collections.emptyList();
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
