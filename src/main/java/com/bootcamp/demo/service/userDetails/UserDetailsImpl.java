package com.bootcamp.demo.service.userDetails;

import com.bootcamp.demo.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UserDetailsImpl implements UserDetails {
     private final String id;
     private final String email;
     @JsonIgnore
     private final String password;

     private final Collection<? extends GrantedAuthority> authorities ;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

   public UserDetailsImpl(String id, String email, String password, Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.email = email;
       this.password = password;
       this.authorities = authorities;
   }
   public static UserDetailsImpl build (User user){
        List<GrantedAuthority> authorityList = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
        return new UserDetailsImpl(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorityList);
   }
   public String getId(){
        return id;
   }
   public String getEmail(){
        return  email;
   }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
