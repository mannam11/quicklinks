package com.links.quicklinks.service;

import com.links.quicklinks.dto.request.SignupRequest;
import com.links.quicklinks.dto.response.SignupResponse;
import com.links.quicklinks.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    public SignupResponse save(SignupRequest signupRequest);
    public User findByEmail(String email) ;
}
