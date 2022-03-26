package com.example.apelsinrestapi.service;
import com.example.apelsinrestapi.dto.ApiResponse;
import com.example.apelsinrestapi.entity.Category;
import com.example.apelsinrestapi.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    final CategoryRepository categoryRepository;
    public ApiResponse add(Category category) {
        categoryRepository.save(category);
        return new ApiResponse("Added", true);
    }

    public ApiResponse edit(Integer id, Category category) {

        Optional<Category> byId = categoryRepository.findById(id);
        if (byId.isEmpty()) return new ApiResponse(" Not found!", false);
        Category edited = byId.get();
        edited.setName(category.getName());
        categoryRepository.save(edited);
        return new ApiResponse("Edited", true);
    }
}
