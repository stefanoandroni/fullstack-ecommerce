package org.stand.springbootecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stand.springbootecommerce.entity.user.ProductCategory;
import org.stand.springbootecommerce.service.ProductCategoryService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200") //TODO: tmp sol
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/category")
public class ProductCategoryController {

    private final ProductCategoryService productCategoryService;

    // GET api/v1/category
    @GetMapping
    public ResponseEntity<List<ProductCategory>> getProductCategories() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(productCategoryService.getProductCategories());
    }

    // GET api/v1/category/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductCategory> getProductCategoryById(@PathVariable(name = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body((productCategoryService.getProductCategoryById(id)));
    }

    // POST api/v1/category {ProductCategory}
    @PostMapping
    public ResponseEntity<ProductCategory> saveProductCategory(@Valid @RequestBody ProductCategory productCategory){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productCategoryService.addProductCategory(productCategory));
    }

}