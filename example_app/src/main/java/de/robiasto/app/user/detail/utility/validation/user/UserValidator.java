package de.robiasto.app.user.detail.utility.validation.user;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.regex.Pattern;

@RequiredArgsConstructor
public class UserValidator implements ConstraintValidator<UserValidation, UserValidationInterface> {

    private final UserValidationServiceInterface userService;


    public boolean isValid(UserValidationInterface userFormResponse, ConstraintValidatorContext context) {
        String email = userFormResponse.getEmail();

        if (email == null) {
            this.setContext(context, "{validation.userName.null}");

            return false;
        }

        if (!Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$").matcher(email).matches()) {
            this.setContext(context, "{validation.userName.invalidEmail}");

            return false;
        }

        if (userFormResponse instanceof ExistingUserValidationInterface updateUserFormResponse) {
            return this.isValid(updateUserFormResponse, context, email);
        }

        return this.isValid(context, email);
    }

    private boolean isValid(ConstraintValidatorContext context, String email) {
        if (this.userService.userWithEmailExists(email)) {
            this.setContext(context, "{validation.userName.alreadyExists}");

            return false;
        }
        return true;
    }

    private boolean isValid(ExistingUserValidationInterface userFormResponse, ConstraintValidatorContext context, String email) {
        if (!this.userService.getUserWithRoles(userFormResponse.getId()).getEmail().equals(email)
                && this.userService.userWithEmailExists(email)
        ) {
            this.setContext(context, "{validation.userName.alreadyExists}");

            return false;
        }

        return true;
    }


    private void setContext(ConstraintValidatorContext context, String errorMessage) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(errorMessage)
               .addPropertyNode("email")
               .addConstraintViolation();
    }
}
