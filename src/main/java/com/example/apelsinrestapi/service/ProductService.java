package com.example.apelsinrestapi.service;
import com.example.apelsinrestapi.dto.ApiResponse;
import com.example.apelsinrestapi.dto.ProductDto;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.entity.Customer;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    public ApiResponse add(ProductDto dto) {
        Category category = new Category();
        category.setId(dto.getCategoryId());
        Category save = categoryRepository.save(category);
        Product product = new Product();
        product.setCategoryId(save);
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setPhoto(dto.getPhoto());
        product.setName(dto.getName());
        productRepository.save(product);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, ProductDto dto) {

        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Product product = byId.get();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setPhoto(dto.getPhoto());
        product.setDescription(dto.getDescription());
        Category category = product.getCategoryId();
        product.setCategoryId(category);
        productRepository.save(product);
        return new ApiResponse("Edited", true);
    }
}
