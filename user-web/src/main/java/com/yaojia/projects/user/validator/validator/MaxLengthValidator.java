package com.yaojia.projects.user.validator.validator;

import com.yaojia.projects.user.validator.MaxLength;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 最大长度注解验证器
 * @author yaojia
 */
public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {

    private int length;

    @Override
    public void initialize(MaxLength constraintAnnotation) {
        this.length = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() <= length;
    }
}
