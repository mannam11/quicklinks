package com.links.quicklinks.service;

import com.links.quicklinks.model.User;

public interface UserService {
    public void save(User user);
    public User findByEmail(String email) ;
}
