package com.links.quicklinks.model;

import com.google.cloud.firestore.annotation.DocumentId;
import com.google.cloud.spring.data.firestore.Document;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Document(collectionName = "account_links")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountLink {

    @DocumentId
    private String id;
    private String title;
    private String url;
    private Category category;
    private LocalDateTime createdAt;
    private String userId;
}
