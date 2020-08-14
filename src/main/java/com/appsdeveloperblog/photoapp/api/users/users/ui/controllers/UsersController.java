package com.appsdeveloperblog.photoapp.api.users.users.ui.controllers;

import com.appsdeveloperblog.photoapp.api.users.users.service.UsersService;
import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import com.appsdeveloperblog.photoapp.api.users.users.ui.model.CreateUserRequestModel;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @GetMapping("/status/check")
    public String status() {
        return "Working";
    }

    @PostMapping
    public String createUser(@Valid  @RequestBody CreateUserRequestModel userDetails) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(STRICT);
        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        usersService.createUser(userDto);
        return "Create user method is called";
    }

}
