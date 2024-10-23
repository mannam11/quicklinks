package com.links.quicklinks.repository;


import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.links.quicklinks.model.User;
import reactor.core.publisher.Mono;

public interface UserRepository extends FirestoreReactiveRepository<User> {
    Mono<User> findByEmail(String email);
    Mono<User> findByUsername(String username);
}
