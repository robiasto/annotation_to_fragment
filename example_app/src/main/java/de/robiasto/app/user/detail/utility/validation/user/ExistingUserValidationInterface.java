package de.robiasto.app.user.detail.utility.validation.user;

import de.robiasto.app.user.domain.UserId;

public interface ExistingUserValidationInterface extends UserValidationInterface{
    String getEmail();
    UserId getId();
}
