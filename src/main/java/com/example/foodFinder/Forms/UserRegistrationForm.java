package com.example.foodFinder.Forms;

import com.example.foodFinder.PasswordMatches;
import com.example.foodFinder.Services.AccountServiceImpl;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@PasswordMatches(message = "{validation.constraints.password.dont.match.message}")
public class UserRegistrationForm {

    @Size(message = "{validation.constraints.name.size.message}", min = 4, max = 30)
    private String name;

    @Email
    @NotNull(message = "{validation.constraints.email.not.null}")
    @Min(4)
    private String emailAdress;

    @NotNull
    @Min(2)
    private String password;

    @NotNull
    @Min(2)
    private String matchingPassword;

    @NotNull(message = "{validation.constraints.city.not.null}")
    private String city;

    @Enumerated
    @NotNull
    private AccountServiceImpl.AccountPlan accountPlan;
}
