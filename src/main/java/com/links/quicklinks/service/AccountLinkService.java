package com.links.quicklinks.service;

import com.links.quicklinks.dto.request.AccountLinkRequest;
import com.links.quicklinks.model.AccountLink;

import java.util.List;

public interface AccountLinkService {
    public void addAccountLink(AccountLinkRequest accountLinkRequest);

    public List<AccountLink> getAllAccountLinks();
}

