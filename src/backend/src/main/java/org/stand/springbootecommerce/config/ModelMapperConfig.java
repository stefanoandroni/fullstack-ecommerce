package org.stand.springbootecommerce.config;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.TypeToken;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.stand.springbootecommerce.dto.UserDTO;
import org.stand.springbootecommerce.dto.request.ProductRequest;
import org.stand.springbootecommerce.dto.response.PageableResponse;
import org.stand.springbootecommerce.dto.response.ProductResponse;
import org.stand.springbootecommerce.entity.user.Product;
import org.stand.springbootecommerce.entity.user.ProductCategory;
import org.stand.springbootecommerce.entity.user.User;
import org.stand.springbootecommerce.service.ProductCategoryService;
import org.stand.springbootecommerce.service.ProductService;

import java.lang.reflect.Type;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
    private final ProductCategoryService productCategoryService;
    private final ProductService productService;

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        /*
            Type
        */
        Type pageProductType = new TypeToken<Page<Product>>() {}.getType();
        //Type pageableResponseProductType = new TypeToken<ProductPageableResponse<Product>>() {}.getType();

        /*
            Converter
        */
        Converter<Long, ProductCategory> categoryIdToCategory = context -> {
            return productCategoryService.getProductCategoryById(context.getSource());
        };

        Converter<ProductCategory, Long> categoryToCategoryId = context -> {
            return context.getSource().getId();
        };

        Converter<Long, Product> productIdToProduct = context -> {
            return productService.getProductById(context.getSource());
        };


        /*
            TypeMap
        */
        TypeMap<ProductRequest, Product> productPostRequestToProduct = modelMapper.createTypeMap(ProductRequest.class, Product.class);

        productPostRequestToProduct.addMappings(
                mapper -> mapper.using(categoryIdToCategory).map(ProductRequest::getCategory, Product::setCategory)
        );

        TypeMap<Product, ProductResponse> productToProductResponse = modelMapper.createTypeMap(Product.class, ProductResponse.class);

        productToProductResponse.addMappings(
                mapper -> mapper.using(categoryToCategoryId).map(Product::getCategory, ProductResponse::setCategoryId)
        );

        TypeMap<User, UserDTO> userToUserDTO = modelMapper.createTypeMap(User.class, UserDTO.class);


        /*
        TypeMap<Page, ProductPageableResponse> pageToProductPageableResponse = modelMapper.createTypeMap(Page.class, ProductPageableResponse.class);

        pageToProductPageableResponse.addMappings(
                mapper -> mapper.map(src -> src.getTotalElements(), ProductPageableResponse::setTotal) // (?) Handle generics
        );

        pageToProductPageableResponse.addMappings(
                mapper -> mapper.map(src -> src.getContent(), ProductPageableResponse::setList) // (?) Handle generics
        );
        */

        return modelMapper;
    }
}