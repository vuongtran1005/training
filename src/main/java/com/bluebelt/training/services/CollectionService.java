package com.bluebelt.training.services;

import com.bluebelt.training.entities.Collection;
import com.bluebelt.training.repositories.CollectionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CollectionService {

    private final CollectionRepository collectionDAO;

    public List<Collection> getAll() {
        return collectionDAO.findAll();
    }

    public Optional<Collection> getById(Integer id) {
        return collectionDAO.findById(id);
    }

    public Collection create(Collection collection) {
        return collectionDAO.save(collection);
    }

    public Collection update(Integer id, Collection collection) {
        return collection;
    }

}
