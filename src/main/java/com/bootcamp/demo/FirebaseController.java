package com.bootcamp.demo;

import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.FirebaseRepositoryFactory;
import com.bootcamp.demo.repository.RepositoryFactory;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Sample RestController
 * Demo - Used for testing purposes
 */
@ConditionalOnProperty("firebaseKey")
@RestController
@RequestMapping(path = "/firebase", produces = APPLICATION_JSON_VALUE)
public class FirebaseController {
     private final RepositoryFactory repositoryFactory;
      @Autowired
    public FirebaseController(RepositoryFactory repositoryFactory) {
        this.repositoryFactory = repositoryFactory;
    }

    @GetMapping("/getAllPaths")
    public Set<String> getAllPaths() {
            return repositoryFactory.createUserRepository().getAllPaths();
    }


    @GetMapping("/testThis")
    public void dummyTest()  {
        User.Gender myGender = User.Gender.MALE;
        User user = new User("Dumitru","Furtuna","c.yahoo.com","oo","aa",myGender);
        System.out.println(repositoryFactory.createUserRepository().save(user));
        Set<User> users = repositoryFactory.createUserRepository().findAll();
        System.out.println("I created " + users.size() + " users ");

        for (User userInUsers : users) {
            System.out.println(userInUsers.toString());
        }

    }

}
