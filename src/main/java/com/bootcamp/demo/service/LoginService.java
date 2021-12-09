package com.bootcamp.demo.service;

import com.bootcamp.demo.dto.builder.UserBuilder;
import com.bootcamp.demo.dto.reply.JwtResponse;
import com.bootcamp.demo.dto.reply.MessageResponse;
import com.bootcamp.demo.dto.request.LoginRequest;
import com.bootcamp.demo.dto.request.RegisterRequest;
import com.bootcamp.demo.model.Session;
import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.SessionRepository;
import com.bootcamp.demo.repository.UserRepository;
import com.bootcamp.demo.security.jwt.JwtUtils;
import com.bootcamp.demo.service.userDetails.UserDetailsImpl;
import com.bootcamp.demo.validation.UserValidationError;
import com.bootcamp.demo.validation.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * LoginService implementation
 * Next to add: forgotPassword()
 */
@Service
public class LoginService implements ILoginService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final SessionRepository sessionRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserValidator userValidator;

    @Autowired
    public LoginService(PasswordEncoder encoder,
                        UserRepository userRepository,
                        SessionRepository sessionRepository,
                        AuthenticationManager authenticationManager,
                        JwtUtils jwtUtils,
                        UserValidator userValidator) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.sessionRepository = sessionRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.userValidator = userValidator;
    }

    @Override
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest){
        User user = UserBuilder.toEntity(registerRequest);

        try {
            userValidator.validateUserAtRegistration(user);
        } catch (UserValidationError e) {
            return new ResponseEntity<>(new MessageResponse(e.getMessage()), HttpStatus.BAD_REQUEST);
        }

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return new ResponseEntity<>(new MessageResponse("Email already in use."), HttpStatus.BAD_REQUEST);
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setId(UUID.randomUUID().toString());
        user.setRole(Collections.singletonList(User.Role.ROLE_USER));
        userRepository.save(user);
        System.out.println(user.toString()); // this is for little logging
        return ResponseEntity.ok(new MessageResponse("Registration was successful"));
    }


    @Override
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
        /*
        boolean successStatus = false;
        UserRepository userRepository = repositoryFactory.createUserRepository();
        SessionRepository sessionRepository = repositoryFactory.createSessionsRepository();
        User loggedUser = userRepository.findByEmail(email);
        if (loggedUser != null && loggedUser.getPassword().equals(password)) {
            Session session = new Session(loggedUser);
            sessionRepository.save(session, session.getId());
            successStatus = true;
        }
        return successStatus;

         */
    }

    /**
     * Sets the endSession to the local time for each Session from the list of active sessions of a user
     * Updates the sessionRepository with each updated Session
     *
     * @param user is the current logged in user that asks to log out
     * @return true if he was successfully logged out from all his devices (all his sessions were ended), else false
     */
    @Override
    public boolean logoutUser(User user) {
        boolean successStatus = true;
        Instant endTime = Instant.now();
        for (Session session : sessionRepository.findSessionsForUserId(user.getId())) {
            session.setEndSession(endTime);
            if (!sessionRepository.updateSession(session)) {
                successStatus = false;
            }
        }
        return successStatus;
    }

    public List<Session> findSessionsFor(String userId) {
        return sessionRepository.findSessionsForUserId(userId);
    }
}
