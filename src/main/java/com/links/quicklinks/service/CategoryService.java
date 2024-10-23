package com.links.quicklinks.service;

import com.links.quicklinks.dto.response.CategoryResponse;
import com.links.quicklinks.model.Category;

import java.util.List;

public interface CategoryService {

    public List<Category> getCategories();

    public void addCategory(CategoryResponse categoryDto);


}
