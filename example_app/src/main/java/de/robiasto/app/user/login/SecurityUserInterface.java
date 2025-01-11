package de.robiasto.app.user.login;

import de.robiasto.app.user.infrastructure.UserId;
import org.springframework.security.core.userdetails.UserDetails;

public interface SecurityUserInterface extends UserDetails {
    String getDisplayName();
    UserId getUserId();
}
