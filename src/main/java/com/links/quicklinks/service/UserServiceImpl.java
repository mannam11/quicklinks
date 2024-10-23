package com.links.quicklinks.service;

import com.links.quicklinks.dto.request.SignupRequest;
import com.links.quicklinks.dto.response.SignupResponse;
import com.links.quicklinks.model.User;
import com.links.quicklinks.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public SignupResponse save(SignupRequest signupRequest) {
        User user = User.builder()
                .email(signupRequest.getEmail())
                .password(bCryptPasswordEncoder.encode(signupRequest.getPassword()))
                .name(signupRequest.getName())
                .role(User.Role.USER)
                .username(generateUsername())
                .build();

        userRepository.save(user).block();
        log.info("User registered with email {} successfully", signupRequest.getEmail());

        return SignupResponse.builder()
                .username(user.getUsername())
                .build();
    }

    private String generateUsername(){
        return UUID.randomUUID().toString().substring(0,10);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).block();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).block();
    }
}
