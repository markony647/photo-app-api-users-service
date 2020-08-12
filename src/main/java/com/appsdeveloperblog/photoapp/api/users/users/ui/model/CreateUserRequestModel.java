package com.appsdeveloperblog.photoapp.api.users.users.ui.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateUserRequestModel {
    @NotNull(message = "First name cannot be null")
    @Size(min = 2, message = "First name must not be less than 2 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, message = "Last name must not be less than 2 characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 16, message = "Password must be equal or grater than 6 and less than 16 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
}
