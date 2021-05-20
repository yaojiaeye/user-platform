package com.yaojia.projects.user.validator;

import com.yaojia.projects.user.validator.validator.EmailValidator;
import com.yaojia.projects.user.validator.validator.PhoneNumberValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author yaojia
 */
@Documented
@Constraint(validatedBy = EmailValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Email {

    String message() default "max_length";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
