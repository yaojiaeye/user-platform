package com.yaojia.projects.user.validator;

import com.yaojia.projects.user.validator.validator.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 非空注解
 *
 * @author yaojia
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {

    String message() default "not_empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
