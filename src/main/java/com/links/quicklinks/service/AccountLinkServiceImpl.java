package com.links.quicklinks.service;

import com.links.quicklinks.dto.request.AccountLinkRequest;
import com.links.quicklinks.model.AccountLink;
import com.links.quicklinks.repository.AccountLinkRepository;
import com.links.quicklinks.utils.CurrentUserDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AccountLinkServiceImpl implements AccountLinkService {

    @Autowired
    private AccountLinkRepository accountLinkRepository;

    @Autowired
    private CurrentUserDetails currentUserDetails;

    @Override
    public void addAccountLink(AccountLinkRequest accountLinkRequest) {

        AccountLink accountLink = AccountLink.builder()
                .url(accountLinkRequest.getUrl())
                .title(accountLinkRequest.getTitle())
                .category(accountLinkRequest.getCategoryName())
                .userId(currentUserDetails.getCurrentUser().getId())
                .build();

        log.info("Added account link for userId: {}", accountLink.getUserId());

        accountLinkRepository.save(accountLink).block();
        log.info("Account link added for user with email : {}",currentUserDetails.getCurrentUser().getEmail());
    }

    @Override
    public List<AccountLink> getAllAccountLinks(String userId) {
        return accountLinkRepository.findAllByUserId(userId).collectList().block();
    }
}
