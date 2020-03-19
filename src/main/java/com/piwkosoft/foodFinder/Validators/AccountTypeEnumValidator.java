package com.piwkosoft.foodFinder.Validators;

import com.piwkosoft.foodFinder.AccountTypeEnumValueExist;
import com.piwkosoft.foodFinder.Persistance.Entities.AccountPlanEntity;

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
