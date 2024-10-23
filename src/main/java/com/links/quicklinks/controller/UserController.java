package com.links.quicklinks.controller;

import com.links.quicklinks.auth.JwtUtil;
import com.links.quicklinks.dto.request.LoginRequest;
import com.links.quicklinks.dto.request.SignupRequest;
import com.links.quicklinks.dto.response.LoginResponse;
import com.links.quicklinks.model.User;
import com.links.quicklinks.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;


    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {

        User existingUser = userService.findByEmail(signupRequest.getEmail());
        if (existingUser != null) {
            log.info("User already exists with email {}", signupRequest.getEmail());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(userService.save(signupRequest),HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        User user = (User) authentication.getPrincipal();
        String token = jwtUtil.generateToken(user);

        LoginResponse response = LoginResponse.builder()
                .token(token)
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
