package com.links.quicklinks.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collectionName = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @DocumentId
    private String id;
    private String name;
    private String email;
    private String picture;
}
