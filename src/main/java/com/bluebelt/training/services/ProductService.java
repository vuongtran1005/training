package com.bluebelt.training.services;

import com.bluebelt.training.entities.Product;
import com.bluebelt.training.entities.common.Slug;
import com.bluebelt.training.exceptions.ResourceNotFoundException;
import com.bluebelt.training.payload.ListResult;
import com.bluebelt.training.pojos.ProductRequest;
import com.bluebelt.training.repositories.CollectionRepository;
import com.bluebelt.training.repositories.ProductRepository;
import com.bluebelt.training.repositories.OptionRepository;
import com.bluebelt.training.utils.PageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.bluebelt.training.utils.AppConstrants.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productDAO;
    private final CollectionRepository collectionDAO;
    private final OptionRepository optionDAO;

    public ListResult<Product> getAll(int page, int per_page, String sort) {
        boolean desc = false;
        if (sort.startsWith("-")) {
            desc = true;
            sort = sort.substring(1);
        }
        return ListResult.from(productDAO.findAll(PageUtils.pageable(page, per_page, sort, desc)));
    }

    public Product getById(Integer id) {
        Product product = productDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException().builder()
                .resourceName(PRODUCT)
                .fieldName(ID)
                .fieldValue(id).build());
        return product;

    }

    public Product add(ProductRequest productRequest) {
        Product product = new Product().builder()
                .title(productRequest.getTitle())
                .description(productRequest.getDescription())

                .collections(productRequest.getCollectionsId().stream()
                        .map(id -> collectionDAO.findById(id).map(exist -> exist)
                        .orElseThrow(() -> new ResourceNotFoundException().builder()
                                .resourceName(COLLECTION)
                                .fieldName(ID)
                                .fieldValue(productRequest.getCollectionsId())
                                .build()))
                        .collect(Collectors.toSet()))

                .seo(Slug.setSlugify(productRequest.getTitle()))
                .options(productRequest.getOptionsId().stream()
                        .map(id -> optionDAO.findById(id).map(exist -> exist)
                        .orElseThrow(() -> new ResourceNotFoundException().builder()
                                .resourceName(OPTION)
                                .fieldName(ID)
                                .fieldValue(productRequest.getOptionsId())
                                .build()))
                        .collect(Collectors.toSet()))
                .tags(productRequest.getTags())
                .build();

        return productDAO.save(product);
    }

    public Product update(Integer productId, ProductRequest productRequest) {
        Product product = productDAO.findById(productId).orElseThrow(() -> new ResourceNotFoundException().builder()
                .resourceName(PRODUCT)
                .fieldName(ID)
                .fieldValue(productId).build());

        product.builder()
                .title(productRequest.getTitle())
                .description(productRequest.getDescription())

                .collections(productRequest.getCollectionsId().stream()
                        .map(id -> collectionDAO.findById(id).map(exist -> exist)
                                .orElseThrow(() -> new ResourceNotFoundException().builder()
                                        .resourceName(COLLECTION)
                                        .fieldName(ID)
                                        .fieldValue(productRequest.getCollectionsId())
                                        .build()))
                        .collect(Collectors.toSet()))

                .seo(Slug.setSlugify(productRequest.getTitle()))
                .options(productRequest.getOptionsId().stream()
                        .map(id -> optionDAO.findById(id).map(exist -> exist)
                                .orElseThrow(() -> new ResourceNotFoundException().builder()
                                        .resourceName(OPTION)
                                        .fieldName(ID)
                                        .fieldValue(productRequest.getOptionsId())
                                        .build()))
                        .collect(Collectors.toSet()))
                .tags(productRequest.getTags())
                .build();

        return productDAO.save(product);
    }

    public void delete(Integer id) {
        Product product = productDAO.findById(id).orElseThrow(() -> new ResourceNotFoundException().builder()
                .resourceName(PRODUCT)
                .fieldName(ID)
                .fieldValue(id).build());
        productDAO.delete(product);
    }

}
