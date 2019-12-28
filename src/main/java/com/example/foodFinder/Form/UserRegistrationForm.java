package com.example.foodFinder.Form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UserRegistrationForm {

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
