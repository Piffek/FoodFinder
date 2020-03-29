package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.Forms.UserRegistrationForm;
import com.piwkosoft.foodFinder.Validators.CustomAnnotations.PasswordMatches;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    @Override
    public void initialize(PasswordMatches passwordMatches) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        UserRegistrationForm userRegistrationForm = (UserRegistrationForm) o;

        if(userRegistrationForm != null && userRegistrationForm.getPassword() != null) {
            return userRegistrationForm.getPassword().equals(userRegistrationForm.getMatchingPassword());
        }

        return false;
    }
}
