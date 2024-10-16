package com.links.quicklinks.dto;

import com.links.quicklinks.model.AccountLink;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public record AccountLinkResponse(String id,String title, String url, String lastUpdatedOn, String categoryName) {

    public static AccountLinkResponse from(AccountLink accountLink) {
        return new AccountLinkResponse(accountLink.getId(),accountLink.getTitle(), accountLink.getUrl(), convertLastUpdatedOn(accountLink.getUpdatedAt()),accountLink.getCategory().getName());
    }

    public static String convertLastUpdatedOn(long lastUpdatedOn) {

        LocalDateTime dateTime = LocalDateTime.ofInstant(Instant.ofEpochSecond(lastUpdatedOn), ZoneId.systemDefault());
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(dateTime, now);

        long seconds = duration.getSeconds();
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) {
            return days + " days ago";
        } else if (hours > 0) {
            return hours + " hours ago";
        } else if (minutes > 0) {
            return minutes + " minutes ago";
        }else{
            return "Just now";
        }
    }
}
