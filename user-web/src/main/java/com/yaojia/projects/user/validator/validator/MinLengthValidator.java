package com.yaojia.projects.user.validator.validator;

import com.yaojia.projects.user.validator.MinLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 最小长度注解验证器
 */
public class MinLengthValidator implements ConstraintValidator<MinLength, String> {

    private int length;

    @Override
    public void initialize(MinLength constraintAnnotation) {
        length = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() >= length;
    }
}
