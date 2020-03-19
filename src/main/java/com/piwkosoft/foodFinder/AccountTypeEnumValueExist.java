package com.piwkosoft.foodFinder;

import com.piwkosoft.foodFinder.Validators.AccountTypeEnumValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountTypeEnumValidator.class)
@Documented
public @interface AccountTypeEnumValueExist {
    String message() default "{validation.constraints.accountPlan.not.exist}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
