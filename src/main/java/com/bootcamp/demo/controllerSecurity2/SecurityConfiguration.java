package com.bootcamp.demo.controllerSecurity2;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/log", "/reg").permitAll()
                .antMatchers("/").access("hasRole('USER')")
                .and()
                .formLogin()
                .loginPage("/log")
                .failureUrl("/login-error")
                .permitAll()
        ;

        http.csrf().disable();
    }
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        var inMemory = auth.inMemoryAuthentication();
        inMemory.withUser("eduard.caraenacherot@gmail.com").password("1234").roles("USER");

    }

}