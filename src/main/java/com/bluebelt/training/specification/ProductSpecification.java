package com.bluebelt.training.specification;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.Product_;
import com.bluebelt.training.entities.common.EBoolean;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;

public final class ProductSpecification {

//    public static Specification<Product> deleteProduct(Integer id){
//        return ((root, query, criteriaBuilder) -> (Predicate) criteriaBuilder.createCriteriaUpdate(Product.class)
//                .set(Product_.IS_DELETE, EBoolean.TRUE)
//                .where(criteriaBuilder.equal(root.get(Product_.ID), id)));
//    }

}
