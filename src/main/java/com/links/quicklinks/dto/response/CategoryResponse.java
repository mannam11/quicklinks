package com.links.quicklinks.dto.response;

import com.links.quicklinks.model.Category;

public record CategoryResponse(String name) {

    public static CategoryResponse fromCategory(Category category) {
        return new CategoryResponse(category.getName());
    }

}
