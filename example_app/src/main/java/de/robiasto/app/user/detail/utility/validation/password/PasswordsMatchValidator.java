package de.robiasto.app.user.detail.utility.validation.password;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordsMatchValidator implements ConstraintValidator<PasswordsMatch, PasswordMatchInterface> {
    @Override
    public void initialize(PasswordsMatch constraintAnnotation) {
        // default implementation ignored
    }

    @Override
    public boolean isValid(PasswordMatchInterface value, ConstraintValidatorContext context) {
        if (value.getPasswordRepeated() != null && !value.getPassword().equals(value.getPasswordRepeated())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("{PasswordsNotMatching}")
                   .addPropertyNode("passwordRepeated")
                   .addConstraintViolation();

            return false;
        }

        return true;
    }
}
