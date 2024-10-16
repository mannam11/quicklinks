package com.links.quicklinks.service;

import com.links.quicklinks.dto.CategoryDto;
import com.links.quicklinks.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategories();

    public void addCategory(CategoryDto categoryDto);


}
