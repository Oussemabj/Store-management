package tn.iit.storemanagment.services.Impl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tn.iit.storemanagment.dto.IdentifiableDto;
import tn.iit.storemanagment.mapper.Mapper;
import tn.iit.storemanagment.models.Identifiable;
import tn.iit.storemanagment.services.CommonService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collections;
import java.util.List;

import static java.util.stream.Collectors.toList;

abstract class CommonServiceImpl<ENTITY extends Identifiable<PK>, DTO extends IdentifiableDto<PK>, PK extends Serializable, REPO extends JpaRepository<ENTITY, PK>>
        implements CommonService<ENTITY, DTO, PK> {

    protected final REPO repository;
    protected final Mapper<ENTITY, DTO> mapper;
    protected final Class<ENTITY> entityClass;
    @PersistenceContext
    protected EntityManager entityManager;


    CommonServiceImpl(final REPO repository, final Mapper<ENTITY, DTO> mapper) {
        this.repository = repository;
        this.mapper = mapper;
        final ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
        this.entityClass = (Class) type.getActualTypeArguments()[0];
    }

    @Override
    @Transactional(readOnly = true)
    public DTO getById(final PK id) {
        if (id == null) {
            throw new NullPointerException("Cannot get an entity with null ID");
        }
        final ENTITY entity = repository.getOne(id);
        return entity != null ? mapper.map(entity) : null;
    }

    @Override
    @Transactional(readOnly = true)
    public DTO getByIdWithFetches(final PK id, final String... fetches) {
        if (id == null) {
            throw new NullPointerException("Cannot get an entity with null ID");
        }
        final StringBuilder queryBuilder = new StringBuilder("SELECT e FROM " + entityClass.getName() + " e");
        if (fetches != null && fetches.length > 0) {
            for (final String fetch : fetches) {
                if (!StringUtils.isEmpty (fetch)) {
                    queryBuilder.append(" LEFT JOIN FETCH e.").append(fetch);
                }
            }
        }
        queryBuilder.append(" WHERE e.id=:id");
        return mapper.map(this.entityManager.createQuery(queryBuilder.toString(), entityClass)
                .setParameter("id", id)
                .getSingleResult());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DTO> getAll() {
        final List<ENTITY> entitiesData = repository.findAll();
        if (CollectionUtils.isEmpty(entitiesData)) {
            return Collections.emptyList();
        }
        return mapToList(entitiesData);
    }

    @Override
    @Transactional(readOnly = true)
    public void deleteById(final PK id) {
        if (id == null) {
            throw new NullPointerException("Cannot delete an entity with null ID");
        }
        repository.deleteById(id);
    }

    /**
     * Map List<ENTITY> to List<DTO>.
     *
     * @param entities the {@link List<ENTITY>}.
     * @return List<DTO> if entities not null or empty otherwise empty list.
     */
    protected List<DTO> mapToList(final List<ENTITY> entities) {
        if (CollectionUtils.isEmpty(entities)) {
            return Collections.emptyList();
        }
        return entities.stream()
                .map(entity -> mapper.map(entity))
                .collect(toList());
    }

    // Used only for tests.
    public void setEntityManager(final EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
