package com.links.quicklinks.controller;

import com.links.quicklinks.dto.AccountLinkRequest;
import com.links.quicklinks.dto.AccountLinkResponse;
import com.links.quicklinks.model.AccountLink;
import com.links.quicklinks.service.AccountLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
public class AccountLinkController {

    @Autowired
    private AccountLinkService accountLinkService;

    @PostMapping
    public ResponseEntity<?> createAccountLink(@RequestBody AccountLinkRequest accountLinkRequest) {

        if (accountLinkRequest == null || accountLinkRequest.url().trim().isEmpty() || accountLinkRequest.title().trim().isEmpty()) {
            return new ResponseEntity<>("Title or Url can't be empty", HttpStatus.BAD_REQUEST);
        }

        accountLinkService.addAccountLink(accountLinkRequest);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<?> getAllAccountLinks() {

        List<AccountLink> accountLinks = accountLinkService.getAllAccountLinks();

        List<AccountLinkResponse> accountLinkResponses = accountLinks.stream()
                .map(AccountLinkResponse::from).toList();

        if (accountLinkResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(accountLinkResponses, HttpStatus.OK);
    }
}
