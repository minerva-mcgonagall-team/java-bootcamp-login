package com.bootcamp.demo;


import com.bootcamp.demo.model.User;
import com.bootcamp.demo.repository.RepositoryFactory;
import com.bootcamp.demo.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
    LoginService loginService;

    @Autowired
    public FirebaseController(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/testRegister")
    public boolean testRegister() {

        User user = new User("RegisterTest", "Om", "florin@hotmail.com", "oo", "aa");
        String hardCodedId = "aaa-bbb-ccc";
        user.setId(hardCodedId);
      //  return loginService.registerUser(user);

       return  false;
    }

}


