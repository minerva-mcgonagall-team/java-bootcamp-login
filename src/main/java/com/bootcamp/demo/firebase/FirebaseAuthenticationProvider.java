package com.bootcamp.demo.firebase;

import com.google.api.core.ApiFuture;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import java.util.concurrent.ExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class FirebaseAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private FirebaseAuth firebaseAuth;

    /*@Override
    public boolean supports(Class<?> authentication) {
        return (FirebaseAuthenticationToken.class.isAssignableFrom(authentication));
    }*/

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication) throws
        AuthenticationException {
    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        //final FirebaseAuthenticationToken authenticationToken = (FirebaseAuthenticationToken) authentication;

        ApiFuture<FirebaseToken> task = firebaseAuth.verifyIdTokenAsync(authentication.getCredentials().toString());
        //FirebaseAuth.getInstance().createUser()

        try {
            FirebaseToken token = task.get();
            return new FirebaseUserDetails(token.getEmail(), token.getUid());
        } catch (InterruptedException | ExecutionException e) {
            throw new SessionAuthenticationException(e.getMessage());
        }
    }
}
