package com.example.basicAuth;

import com.example.basicAuth.model.User;
import com.example.basicAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class Start {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    @PostConstruct
    public void addUsersToDb(){

        User richard = new User();
        richard.setUsername("richard");
        richard.setPassword("test1234");

        User toni = new User();
        toni.setUsername("toni");
        toni.setPassword("flusensieb");

        userService.saveUser(richard);
        userService.saveUser(toni);
    }
}
