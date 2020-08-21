package com.appsdeveloperblog.photoapp.api.users.users.service;

import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
}
