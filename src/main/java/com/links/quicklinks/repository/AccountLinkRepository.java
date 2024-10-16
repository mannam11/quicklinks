package com.links.quicklinks.repository;

import com.google.cloud.spring.data.firestore.FirestoreReactiveRepository;
import com.links.quicklinks.model.AccountLink;

public interface AccountLinkRepository extends FirestoreReactiveRepository<AccountLink> {
}
