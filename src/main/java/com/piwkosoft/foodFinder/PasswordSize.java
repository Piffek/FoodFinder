package com.piwkosoft.foodFinder;


import com.piwkosoft.foodFinder.Validators.PasswordSizeValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PasswordSizeValidator.class)
@Documented
public @interface PasswordSize {
    String message() default "{validation.constranits.password.size.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int value();
}
