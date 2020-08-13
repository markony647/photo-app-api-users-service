package com.appsdeveloperblog.photoapp.api.users.users.service;

import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;

import java.util.UUID;

public class UsersServiceImpl implements UsersService {

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        return null;
    }
}
