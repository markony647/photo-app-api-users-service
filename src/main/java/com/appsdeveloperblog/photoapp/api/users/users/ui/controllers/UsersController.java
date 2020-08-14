package com.appsdeveloperblog.photoapp.api.users.users.ui.controllers;

import com.appsdeveloperblog.photoapp.api.users.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserReponseModel;
import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UsersController {

    private UsersService usersService;
    private ModelMapper modelMapper;

    @Autowired
    public UsersController(UsersService usersService, ModelMapper modelMapper) {
        this.usersService = usersService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public ResponseEntity<CreateUserReponseModel> createUser(@Valid  @RequestBody CreateUserRequestModel userDetails) {
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        UserDto createdUser = usersService.createUser(userDto);
        CreateUserReponseModel returnValue = modelMapper.map(createdUser, CreateUserReponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

}
