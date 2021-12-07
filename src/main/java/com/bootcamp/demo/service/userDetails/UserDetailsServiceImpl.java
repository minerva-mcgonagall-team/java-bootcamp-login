package com.bootcamp.demo.service.userDetails;

import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.RepositoryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final RepositoryFactory repositoryFactory;

    @Autowired
    public UserDetailsServiceImpl(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        User user = repositoryFactory.createUserRepository().findByEmail(emailAddress);
        return UserDetailsImpl.build(user);
    }
}
