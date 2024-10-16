package com.links.quicklinks.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Document(collectionName = "category")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @DocumentId
    private String id;

    private String name;
}
