package com.appsdeveloperblog.photoapp.api.users.users.shared;

import com.appsdeveloperblog.photoapp.api.users.users.ui.model.AlbumResponseModel;
import lombok.Data;

import java.util.List;

@Data
public class UserDto {
    private String userId;
    private String firstName;
    private String lastName;
    private String password;
    private String encryptedPassword;
    private String email;
    private List<AlbumResponseModel> albums;
}
