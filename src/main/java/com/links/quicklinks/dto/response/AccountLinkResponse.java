package com.links.quicklinks.dto.response;

import com.links.quicklinks.model.AccountLink;

public record AccountLinkResponse(String id,String title, String url, String categoryName) {

    public static AccountLinkResponse from(AccountLink accountLink) {
        return new AccountLinkResponse(accountLink.getId(),accountLink.getTitle(), accountLink.getUrl(), accountLink.getCategory().getName());
    }

}
