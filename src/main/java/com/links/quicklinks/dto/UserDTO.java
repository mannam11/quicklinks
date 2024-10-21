package com.links.quicklinks.dto;

import com.links.quicklinks.model.User;

public record UserDTO(String name, String email, String pictureUrl) {

    public static UserDTO toUserDTO(User user) {
        return new UserDTO(user.getName(), user.getEmail(), user.getPicture());
    }
}
