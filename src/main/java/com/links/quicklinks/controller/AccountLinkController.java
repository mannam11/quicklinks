package com.links.quicklinks.controller;

import com.links.quicklinks.dto.request.AccountLinkRequest;
import com.links.quicklinks.dto.request.GetLinksRequest;
import com.links.quicklinks.dto.response.AccountLinkResponse;
import com.links.quicklinks.model.AccountLink;
import com.links.quicklinks.model.Category;
import com.links.quicklinks.model.User;
import com.links.quicklinks.service.AccountLinkService;
import com.links.quicklinks.service.CategoryService;
import com.links.quicklinks.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/link")
@Slf4j
public class AccountLinkController {

    @Autowired
    private AccountLinkService accountLinkService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createAccountLink(@Valid @RequestBody AccountLinkRequest accountLinkRequest) {

        Category category = categoryService.getCategoryByName(accountLinkRequest.getCategoryName());
        if(category == null) {
            throw new RuntimeException("Invalid Category");
        }

        accountLinkService.addAccountLink(accountLinkRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> getAllAccountLinks(@RequestParam String username) {

        User user = userService.findByUsername(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        List<AccountLink> accountLinks = null;

        try {
            accountLinks = accountLinkService.getAllAccountLinks(user.getId());
        } catch (Exception e) {
            log.error("Error retrieving account links for user {}: {}", user.getEmail(), e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        List<AccountLinkResponse> accountLinkResponses = accountLinks.stream()
                .map(AccountLinkResponse::from).toList();

        log.info("Getting all accountLinks fom user {}", user.getEmail());
        return new ResponseEntity<>(accountLinkResponses, HttpStatus.OK);
    }
}
