package com.links.quicklinks.controller;

import com.links.quicklinks.dto.UserDTO;
import com.links.quicklinks.model.User;
import com.links.quicklinks.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/create")
    public ResponseEntity<?> getCurrentUser(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");

        User existingUserOpt = userService.findByEmail(email);

        if (existingUserOpt != null) {
            log.info("User already exists: {}", email);
            return ResponseEntity.ok(UserDTO.toUserDTO(existingUserOpt));
        }

        User newUser = User.builder()
                .name(principal.getAttribute("name"))
                .email(email)
                .picture(principal.getAttribute("picture"))
                .build();

        userService.save(newUser);
        log.info("New user created: {}", email);

        return ResponseEntity.status(HttpStatus.CREATED).body(UserDTO.toUserDTO(newUser));
    }
}
