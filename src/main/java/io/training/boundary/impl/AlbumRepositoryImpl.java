package io.training.boundary.impl;

import io.training.boundary.AlbumRepository;
import io.training.entity.Album;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class AlbumRepositoryImpl extends CrudAbstractBeanImpl<Album, Long> implements AlbumRepository {
    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;

    public AlbumRepositoryImpl() {
        super(Album.class);
    }
    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
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
