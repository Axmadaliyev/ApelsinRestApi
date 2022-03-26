package com.example.apelsinrestapi.controller;

import com.example.apelsinrestapi.dto.ApiResponse;
import com.example.apelsinrestapi.dto.ProductDto;
import com.example.apelsinrestapi.entity.Product;
import com.example.apelsinrestapi.repository.CategoryRepository;
import com.example.apelsinrestapi.repository.ProductRepository;
import com.example.apelsinrestapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
public class ProductController {
    final ProductRepository productRepository;
    final CategoryRepository categoryRepository;
    final ProductService productService;
    @GetMapping
    public HttpEntity<?> getAll() {
        List<Product> all = productRepository.findAllByActiveTrue();
        return ResponseEntity.ok().body(all);
    }
    @GetMapping("/{id}")
    public HttpEntity<?> getOne(@PathVariable Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        return ResponseEntity.status(byId.isEmpty() ?
                HttpStatus.NOT_FOUND : HttpStatus.OK).body(byId.orElse(new Product()));
    }
    @PostMapping
    public HttpEntity<?> add(@RequestBody ProductDto dto) {
        ApiResponse response = productService.add(dto);
        return ResponseEntity.status(response.isSuccess() ? 201 : 409).body(response);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> edit(@PathVariable Integer id, @RequestBody ProductDto productDto) {
        ApiResponse response = productService.edit(id, productDto);
        return ResponseEntity.status(response.isSuccess() ? 200 : 409).body(response);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> delete(@PathVariable Integer id) {
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isEmpty()) return ResponseEntity.status(404).body("NOT FOUND");
        Product product = byId.get();
        product.setActive(false);
        productRepository.save(product);
        return ResponseEntity.ok().body("DELETED!");
    }

    @GetMapping("/high_demand_products")
    public HttpEntity<?> getHigh_demand_products() {
        List<Product> all = productRepository.getHigh_demand_products();
        return ResponseEntity.ok().body(all);
    }

    @GetMapping("/bulk_products")
    public HttpEntity<?> getBulk_products() {
        List<Product> all = productRepository.getBulk_products();
        return ResponseEntity.ok().body(all);
    }
}
