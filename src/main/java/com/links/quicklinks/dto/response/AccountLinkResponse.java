package com.links.quicklinks.dto.response;

import com.links.quicklinks.model.AccountLink;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountLinkResponse {
    private String id;
    private String title;
    private String url;
    private String category;

    public static AccountLinkResponse from(AccountLink accountLink) {
        return AccountLinkResponse.builder()
                .id(accountLink.getId())
                .title(accountLink.getTitle())
                .url(accountLink.getUrl())
                .category(accountLink.getCategory())
                .build();
    }
}
