package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.PasswordSize;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordSizeValidator implements ConstraintValidator<PasswordSize, Object> {
    private int defaultMinPasswordSize;

    @Override
    public void initialize(PasswordSize constraintAnnotation) {
        this.defaultMinPasswordSize = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String string = (String) o;
        return string.length() > defaultMinPasswordSize;
    }
}
