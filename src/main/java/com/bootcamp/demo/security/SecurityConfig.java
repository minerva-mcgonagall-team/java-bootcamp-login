package com.bootcamp.demo.security;

import com.bootcamp.demo.firebase.FirebaseAuthenticationProvider;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private FirebaseAuthenticationProvider authenticationProvider;

    @Autowired
    public SecurityFilter tokenAuthenticationFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Arrays.asList(authenticationProvider));
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            .disable()
            .httpBasic()
            .disable()
            .authorizeRequests()
            .antMatchers("/login/**").permitAll()
            .antMatchers("/public/**").permitAll()
            .antMatchers("/actuator/health").permitAll()
            .anyRequest().authenticated()
            .and()
            .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

}
