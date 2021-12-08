package com.bootcamp.demo.service;

import com.bootcamp.demo.dto.reply.ProfileResponse;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.RepositoryFactory;
import com.bootcamp.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class ProfileService {
    private final RepositoryFactory repositoryFactory;

    public ProfileService(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    public ProfileResponse getProfile (String userID) throws ExecutionException, InterruptedException {
        UserRepository userRepository = repositoryFactory.createUserRepository();
        User user = userRepository.findById(userID);
        return new ProfileResponse(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
    }
}
