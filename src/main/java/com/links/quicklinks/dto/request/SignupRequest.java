package com.links.quicklinks.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignupRequest {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String name;
}
