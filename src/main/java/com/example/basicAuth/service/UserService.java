package com.example.basicAuth.service;

import com.example.basicAuth.model.User;
import com.example.basicAuth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User source){
        User toPersist = new User();
        toPersist.setUsername(source.getUsername());
        toPersist.setPassword(passwordEncoder.encode(source.getPassword()));
        return userRepository.save(toPersist);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User optionalUser = userRepository.findById(s).get();
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (optionalUser.getUsername().equals("richard")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        if (optionalUser.getUsername().equals("toni")){
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }
        return new org.springframework.security.core.userdetails.User(optionalUser.getUsername(), optionalUser.getPassword(),authorities);
    }
}
