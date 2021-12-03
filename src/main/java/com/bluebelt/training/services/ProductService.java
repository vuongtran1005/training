package com.bluebelt.training.services;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.common.EBoolean;
import com.bluebelt.training.repositories.ProductRepository;
import com.bluebelt.training.specification.ProductSpecification;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductRepository productDAO;

    public List<Product> getAll() {
        return productDAO.findAll();
    }

    public Optional<Product> getById(Integer id) {
        return productDAO.findById(id);
    }

    public Product create(Product product) {
        return productDAO.save(product);
    }

    public Product update(Integer id, Product product) {
        return product;
    }

}
