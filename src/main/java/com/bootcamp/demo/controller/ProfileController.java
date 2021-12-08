package com.bootcamp.demo.controller;

import com.bootcamp.demo.dto.reply.ProfileResponse;
import com.bootcamp.demo.service.ProfileService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping ("/api/profile")
public class ProfileController {
    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @RequestMapping("/{userID}")
    public ProfileResponse getProfile (@PathVariable String userID) throws ExecutionException, InterruptedException {
        return profileService.getProfile(userID);
    }
}
