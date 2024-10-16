package com.links.quicklinks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class QuickLinksApplication {

    public static void main(String[] args) {
        SpringApplication.run(QuickLinksApplication.class, args);
    }

    @RestController
    public static class RootController{

        @GetMapping("/health")
        public ResponseEntity<?> getHealth(){
            return ResponseEntity.ok("OK");
        }

        @GetMapping
        public ResponseEntity<?> getDefaultMessage(){
            return ResponseEntity.ok("Hello from QuickLinks Application...");
        }

    }
}
