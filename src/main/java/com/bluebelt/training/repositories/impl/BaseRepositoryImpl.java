package com.bluebelt.training.repositories.impl;

import com.bluebelt.training.entities.BaseEntity;
import com.bluebelt.training.repositories.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.util.Date;
import java.util.Optional;

public class BaseRepositoryImpl<T extends BaseEntity> extends SimpleJpaRepository<T, Integer> implements BaseRepository<T, Integer> {

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
    }

    @Override
    public Optional<T> update(Integer id, T t) {
        return findById(id).map(exist -> {
            t.setId(exist.getId());
            t.setCreatedAt(exist.getCreatedAt());
            t.setUpdatedAt(new Date());
            return save(t);
        });
    }

}
