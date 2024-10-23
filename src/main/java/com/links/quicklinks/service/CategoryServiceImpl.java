package com.links.quicklinks.service;

import com.links.quicklinks.dto.response.CategoryResponse;
import com.links.quicklinks.model.Category;
import com.links.quicklinks.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll().collectList().block();
    }

    @Override
    public void addCategory(CategoryResponse categoryDto) {

        Category category = Category.builder()
                .name(categoryDto.name())
                .build();

        categoryRepository.save(category).subscribe();
    }
}
