package com.appsdeveloperblog.photoapp.api.users.users.shared;

import lombok.Data;

@Data
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private String email;
}
