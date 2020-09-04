package com.appsdeveloperblog.photoapp.api.users.users.ui.controllers;

import com.appsdeveloperblog.photoapp.api.users.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserResponseModel;
import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;
    private final ModelMapper modelMapper;
    private final Environment env;

    @Autowired
    public UsersController(UsersService usersService, ModelMapper modelMapper, Environment env) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
        this.env = env;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working with the secret key " + env.getProperty("token.secret");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid  @RequestBody CreateUserRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);
        CreateUserResponseModel returnValue = modelMapper.map(createdUser, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
