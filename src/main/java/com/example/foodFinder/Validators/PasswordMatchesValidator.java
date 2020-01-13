package com.example.foodFinder.Validators;

import com.example.foodFinder.Forms.UserRegistrationForm;
import com.example.foodFinder.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationForm userRegistrationForm = (UserRegistrationForm) o;
        return userRegistrationForm.getPassword().equals(userRegistrationForm.getMatchingPassword());
    }
}
