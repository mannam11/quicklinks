package com.links.quicklinks.service;

import com.links.quicklinks.dto.CategoryDto;
import com.links.quicklinks.model.Category;
import com.links.quicklinks.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.findAll().collectList().block();
    }

    @Override
    public void addCategory(CategoryDto categoryDto) {

        Category category = Category.builder()
                .name(categoryDto.name())
                .build();

        categoryRepository.save(category).subscribe();
    }
}
