package com.example.foodFinder.Requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationRequest {

    @NotNull
    @Size(min = 4, max = 30)
    private String name;

    @Email
    @NotNull
    @Min(4)
    private String emailAdress;

    @NotNull
    @Min(2)
    private String password;
}
