package de.robiasto.app.user.detail.utility.validation.user;

import de.robiasto.app.user.infrastructure.UserId;

public interface ExistingUserValidationInterface extends UserValidationInterface{
    String getEmail();
    UserId getId();
}
