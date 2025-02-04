package de.robiasto.app.user.detail.utility.validation.user;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UserValidator.class)
public @interface UserValidation {
    String message() default "Invalid data";  // Add a default message

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
