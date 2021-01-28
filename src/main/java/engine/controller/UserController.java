package engine.controller;


import engine.exceptions.TakenEmailException;
import engine.model.User;
import engine.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping("/api/register")
    public void register(@Valid @RequestBody User user) {
        boolean existent = userServiceImpl.getByEmail(user.getEmail()).isPresent();
        if (existent) {
            throw new TakenEmailException(user.getEmail());
        }

        userServiceImpl.create(user);
    }
}
