package de.robiasto.app.user.detail.web.update;

import de.robiasto.app.user.detail.utility.AbstractUserResponse;
import de.robiasto.app.user.detail.utility.validation.user.ExistingUserValidationInterface;
import de.robiasto.app.user.detail.utility.validation.user.UserValidation;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.Setter;

import java.util.UUID;

@Setter
@UserValidation
class UserUpdateResponse extends AbstractUserResponse implements ExistingUserValidationInterface {
    private UUID id;

    public UserId getId() {
        return new UserId(this.id);
    }
}
