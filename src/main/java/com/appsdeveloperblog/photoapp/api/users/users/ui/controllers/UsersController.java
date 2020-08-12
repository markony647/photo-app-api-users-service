package com.appsdeveloperblog.photoapp.api.users.users.ui.controllers;

import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserRequestModel;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public String createUser(@Valid  @RequestBody CreateUserRequestModel userDetails) {
        return "Create user method is called";
    }

}
