package com.links.quicklinks.dto;

import com.links.quicklinks.model.Category;

public record CategoryDto(String name) {

    public static CategoryDto fromCategory(Category category) {
        return new CategoryDto(category.getName());
    }

}
