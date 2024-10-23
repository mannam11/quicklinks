package com.links.quicklinks.controller;

import com.links.quicklinks.dto.request.AccountLinkRequest;
import com.links.quicklinks.dto.response.AccountLinkResponse;
import com.links.quicklinks.model.AccountLink;
import com.links.quicklinks.service.AccountLinkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/link")
@Slf4j
public class AccountLinkController {

    @Autowired
    private AccountLinkService accountLinkService;

    @PostMapping
    public ResponseEntity<?> createAccountLink(@RequestBody AccountLinkRequest accountLinkRequest) {

        log.info("Validating account link : {}", accountLinkRequest);
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
