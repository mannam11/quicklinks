package com.links.quicklinks.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@NotNull
public class AccountLinkRequest {
    private String title;
    private String url;
    private String categoryName;
}
