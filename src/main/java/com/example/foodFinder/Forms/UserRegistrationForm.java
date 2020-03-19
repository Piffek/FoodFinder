package com.example.foodFinder.Forms;

import com.example.foodFinder.AccountTypeEnumValueExist;
import com.example.foodFinder.PasswordMatches;
import com.example.foodFinder.PasswordSize;
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

    @Email
    @NotNull(message = "{validation.constraints.email.not.null}")
    private String emailAdress;

    @PasswordSize(4)
    private String password;

    private String matchingPassword;

    @NotNull(message = "{validation.constraints.city.not.null}")
    private String city;

    @NotNull(message = "{validation.constraints.accountPlan.not.null}")
    @AccountTypeEnumValueExist
    private String accountPlan;
}
