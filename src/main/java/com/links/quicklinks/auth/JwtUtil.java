package com.links.quicklinks.auth;

import org.springframework.security.core.userdetails.UserDetails;

public interface JwtUtil {
    public String generateToken(UserDetails userDetails);
}
