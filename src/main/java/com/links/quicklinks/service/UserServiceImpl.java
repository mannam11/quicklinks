package com.links.quicklinks.service;

import com.links.quicklinks.model.User;
import com.links.quicklinks.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public void save(User user) {
        try{
            userRepository.save(user).block();
        }catch(Exception e){
            log.error(e.getMessage());
        }
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).block();
    }

}
