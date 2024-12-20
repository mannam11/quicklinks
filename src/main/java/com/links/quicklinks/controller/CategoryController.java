package com.links.quicklinks.controller;

import com.links.quicklinks.dto.CategoryDTO;
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
    public ResponseEntity<?> createCategory(@RequestBody CategoryDTO categoryDto) {

        categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {

        List<Category> categories = categoryService.getCategories();

        List<CategoryDTO> categoryDtos = categories.stream()
                .map(CategoryDTO::from)
                .toList();

        return new ResponseEntity<>(categoryDtos, HttpStatus.OK);
    }

}
