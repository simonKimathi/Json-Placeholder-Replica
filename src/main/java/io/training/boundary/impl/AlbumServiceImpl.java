package io.training.boundary.impl;

import io.training.boundary.AlbumService;
import io.training.entity.Album;
import io.training.entity.Comment;
import io.training.util.Constants;
import io.training.util.DeleteStatus;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class AlbumServiceImpl extends CrudAbstractBeanImpl<Album, Long> implements AlbumService {
    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public AlbumServiceImpl() {
        super(Album.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public boolean delete(Album entity) {
        entity.setDeleteStatus(DeleteStatus.DELETED);
        Album edit= update(entity);
        return edit != null;
    }

    @Override
    public List<Album> findAllByUserId(long userId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Album> query = criteriaBuilder.createQuery(Album.class);
        Root<Album> from = query.from(Album.class);
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
