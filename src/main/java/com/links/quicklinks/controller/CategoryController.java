package com.links.quicklinks.controller;

import com.links.quicklinks.dto.response.CategoryResponse;
import com.links.quicklinks.model.Category;
import com.links.quicklinks.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<?> createCategory(@RequestBody CategoryResponse categoryDto) {

        if(categoryDto == null || categoryDto.name().trim().isEmpty()){
            throw new RuntimeException("Category name cannot be empty");
        }

        categoryService.addCategory(categoryDto);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {

        List<Category> categories = categoryService.getCategories();

        List<CategoryResponse> categoryDtos = categories.stream().map(CategoryResponse::fromCategory).toList();

        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

}
