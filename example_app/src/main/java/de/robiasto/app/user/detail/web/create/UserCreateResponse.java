package de.robiasto.app.user.detail.web.create;

import de.robiasto.app.user.detail.utility.AbstractUserResponse;
import de.robiasto.app.user.detail.utility.validation.password.PasswordMatchInterface;
import de.robiasto.app.user.detail.utility.validation.password.PasswordsMatch;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@PasswordsMatch
class UserCreateResponse extends AbstractUserResponse implements PasswordMatchInterface {
    @NotBlank
    private String password;

    @NotBlank
    private String passwordRepeated;
}
