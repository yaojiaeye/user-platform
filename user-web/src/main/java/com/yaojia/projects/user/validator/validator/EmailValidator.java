package com.yaojia.projects.user.validator.validator;

import com.yaojia.projects.user.validator.Email;
import com.yaojia.projects.user.validator.PhoneNumber;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author yaojia
 */
public class EmailValidator implements ConstraintValidator<Email, String> {

    private static final String EMAIL_REGEX = "^(.+)@(\\S+)$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return false;
        }
        if (email.endsWith(".")) {
            return false;
        }
        Matcher emailMatcher = EMAIL_PATTERN.matcher(email);
        if (!emailMatcher.matches()) {
            return false;
        }
        return true;
    }
}
