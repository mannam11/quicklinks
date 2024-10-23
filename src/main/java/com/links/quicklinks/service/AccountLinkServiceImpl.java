package com.links.quicklinks.service;

import com.links.quicklinks.dto.request.AccountLinkRequest;
import com.links.quicklinks.model.AccountLink;
import com.links.quicklinks.model.Category;
import com.links.quicklinks.repository.AccountLinkRepository;
import com.links.quicklinks.repository.CategoryRepository;
import com.links.quicklinks.utils.CurrentUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class AccountLinkServiceImpl implements AccountLinkService {

    @Autowired
    private AccountLinkRepository accountLinkRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CurrentUserDetails currentUser;

    @Override
    public void addAccountLink(AccountLinkRequest accountLinkRequest) {

        log.info("Fetching category by name {}",accountLinkRequest.categoryName());
        Category category = categoryRepository.findByName(accountLinkRequest.categoryName()).block();

        if(category == null) {
            throw new RuntimeException("Invalid category");
        }

        AccountLink accountLink = AccountLink.builder()
                .url(accountLinkRequest.url())
                .title(accountLinkRequest.title())
                .category(category)
                .userId(currentUser.getCurrentUser().getId())
                .build();

        accountLinkRepository.save(accountLink).subscribe();
    }

    @Override
    public List<AccountLink> getAllAccountLinks() {
        return accountLinkRepository.findAll().collectList().block();
    }
}
