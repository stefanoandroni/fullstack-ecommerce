package org.stand.springbootecommerce.service.impl;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.stand.springbootecommerce.entity.user.ProductCategory;
import org.stand.springbootecommerce.repository.ProductCategoryRepository;
import org.stand.springbootecommerce.service.ProductCategoryService;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    private final ProductCategoryRepository productCategoryRepository;

    @Override
    public List<ProductCategory> getProductCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public ProductCategory getProductCategoryById(Long id) {
        return productCategoryRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException("ProductCategory with id='%d' not found".formatted(id)));
    }

    @Override
    public ProductCategory addProductCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }
}