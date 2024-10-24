package com.links.quicklinks.repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.links.quicklinks.model.AccountLink;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface AccountLinkRepository extends FirestoreReactiveRepository<AccountLink> {
    Flux<AccountLink> findAllByUserId(String id);
}

