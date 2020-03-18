package com.example.foodFinder.Validators;

import com.example.foodFinder.AccountTypeEnumValueExist;
import com.example.foodFinder.Persistance.Entities.AccountPlanEntity;
import com.example.foodFinder.Services.AccountServiceImpl;

import com.example.foodFinder.Services.Interfaces.AccountService;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AccountTypeEnumValidator implements ConstraintValidator<AccountTypeEnumValueExist, Object> {

    @Override
    public void initialize(AccountTypeEnumValueExist constraintAnnotation) {

    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String value = (String) o;
        try {
            AccountPlanEntity.AccountPlan.lookup(value);
        } catch (RuntimeException e) {
            return false;
        }

        return true;
    }
}
