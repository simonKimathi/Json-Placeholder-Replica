package io.training.boundary.impl;

import io.training.boundary.PhotoService;
import io.training.entity.Photo;
import io.training.util.Constants;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Stateless
public class PhotoServiceImpl extends CrudAbstractBeanImpl<Photo,Long> implements PhotoService {
    @PersistenceContext(name = Constants.PERSISTENCE_UNIT_NAME)
    private EntityManager entityManager;
    public PhotoServiceImpl() {
        super(Photo.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public List<Photo> findAllByAlbumId(long postId) {
        CriteriaBuilder criteriaBuilder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Photo> query = criteriaBuilder.createQuery(Photo.class);
        Root<Photo> from = query.from(Photo.class);
        query.select(from)
                .where(
                        criteriaBuilder.equal(
                                from.get("album").get("id"),
                                postId
                        )
                );
        return getEntityManager().createQuery(query).getResultList();
    }
}
