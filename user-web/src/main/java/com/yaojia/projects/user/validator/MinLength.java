package com.yaojia.projects.user.validator;

import com.yaojia.projects.user.validator.validator.MinLengthValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 最小长度注解
 *
 * @author yaojia
 */
@Documented
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinLengthValidator.class)
public @interface MinLength {

    String message() default "min_length";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int value();
}
