package com.bluebelt.training.repositories;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.common.EBoolean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {

    List<Product> findByIsDeleted(Enum isDeleted);
}
