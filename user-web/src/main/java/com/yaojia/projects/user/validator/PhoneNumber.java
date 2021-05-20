package com.yaojia.projects.user.validator;

import com.yaojia.projects.user.validator.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import java.lang.annotation.*;

/**
 * @author yaojia
 */
@Documented
@Constraint(validatedBy = PhoneNumberValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PhoneNumber {
    String message() default "Invalid phone number";
    Class[] groups() default {};
    Class[] payload() default {};
}
