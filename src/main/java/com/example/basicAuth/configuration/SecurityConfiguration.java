package com.example.basicAuth.configuration;

import com.example.basicAuth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
            .anyRequest().authenticated()
            .regexMatchers("/api/.*").hasRole("ADMIN")
            .regexMatchers("/toni").hasAnyRole("USER", "ADMIN")
            .regexMatchers("/login").permitAll()
            .and().httpBasic()
            .and().csrf().disable()
            .formLogin().loginPage("/login").defaultSuccessUrl("/meineSeite").and().logout();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

}
