package com.links.quicklinks.repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.links.quicklinks.model.Category;
import reactor.core.publisher.Mono;

public interface CategoryRepository extends FirestoreReactiveRepository<Category> {
    public Mono<Category> findByName(String name);
}
