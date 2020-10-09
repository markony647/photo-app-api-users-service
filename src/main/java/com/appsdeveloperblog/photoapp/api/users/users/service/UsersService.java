package com.appsdeveloperblog.photoapp.api.users.users.service;

import com.appsdeveloperblog.photoapp.api.users.users.shared.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UsersService extends UserDetailsService {

    UserDto createUser(UserDto userDto);
    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserIdUsingRestTemplate(String userId);

    UserDto getUserByUserIdUsingFeign(String userId);
}
