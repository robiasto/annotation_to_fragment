package de.robiasto.app.infrastructure.utility.security;

import de.robiasto.app.user.infrastructure.UserId;
import de.robiasto.app.user.infrastructure.UserRole;
import de.robiasto.app.user.login.SecurityUserInterface;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class Security {
    private Security() {}

    public static boolean isAdmin() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            return authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(UserRole.ROLE_ADMIN.toString()));
        }

        return false;
    }

    public static boolean isCurrentUser(UserId userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            return ((SecurityUserInterface)authentication.getPrincipal()).getUserId().equals(userId);
        }

        return false;
    }
}
