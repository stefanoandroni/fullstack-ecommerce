package org.stand.springbootecommerce.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.stand.springbootecommerce.dto.request.ProductRequest;
import org.stand.springbootecommerce.dto.response.PageableResponse;
import org.stand.springbootecommerce.dto.response.ProductResponse;
import org.stand.springbootecommerce.entity.user.Product;
import org.stand.springbootecommerce.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200") //TODO: tmp sol
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/product")
public class ProductController {
    private final Logger LOG = LoggerFactory.getLogger(ProductController.class);
    private final ProductService productService;
    private final ModelMapper modelMapper;

    // GET api/v1/product
    // GET api/v1/product?q={Abc}
    @GetMapping
    public ResponseEntity<PageableResponse> getProducts(
            // @RequestParam(name = "category", required = false) String category,
            @RequestParam(name = "q", required = false) String query,
            @RequestParam(name = "pageNumber", required = true) Integer pageNumber, // -> pageIndex
            @RequestParam(name = "pageSize", required = true) Integer pageSize
            ) throws InterruptedException {
        // TODO: use modelMappings
        Page<Product> productPage = productService.getProducts(query, pageNumber, pageSize);
        PageableResponse<ProductResponse> pageableResponse = new PageableResponse<ProductResponse>(productPage.getTotalElements(), productPage.getContent().stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(pageableResponse);
    }

    // POST api/v1/product {ProductRequest}
    @PostMapping
    public ResponseEntity<ProductResponse> saveProduct(@Valid @RequestBody ProductRequest productRequest) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        modelMapper.map(
                                productService.addProduct(
                                        modelMapper.map(productRequest, Product.class)
                                ),
                                ProductResponse.class
                        )
                );
    }

    // GET api/v1/product/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable(name = "id") Long id) {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(
                        modelMapper.map(productService.getProductById(id), ProductResponse.class)
                );
    }

}