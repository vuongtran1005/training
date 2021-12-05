package com.bluebelt.training.repositories;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.common.EBoolean;
import com.bluebelt.training.repositories.custom.ProductCustomRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends BaseRepository<Product, Integer>, ProductCustomRepository {

    List<Product> findByIsDeleted(Enum isDeleted);

    //    @Transactional
//    @Modifying
//    @Query("UPDATE Product SET title = :title WHERE id = :id")
//    Optional<Product> updateTitle(Integer id, String title);
}
