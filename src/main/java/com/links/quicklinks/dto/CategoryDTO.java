package com.links.quicklinks.dto;

import com.links.quicklinks.model.Category;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CategoryDTO{

    private String name;

    public static CategoryDTO from(Category category) {
       return CategoryDTO.builder()
               .name(category.getName())
               .build();
    }

}
