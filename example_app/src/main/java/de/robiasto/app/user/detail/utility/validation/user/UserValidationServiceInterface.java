package de.robiasto.app.user.detail.utility.validation.user;

import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.domain.UserId;

interface UserValidationServiceInterface {

    boolean userWithEmailExists(String email);

    UserEntity getUserWithRoles(UserId userId);
}
