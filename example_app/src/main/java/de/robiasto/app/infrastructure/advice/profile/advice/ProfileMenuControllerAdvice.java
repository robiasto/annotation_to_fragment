package de.robiasto.app.infrastructure.advice.profile.advice;

import de.robiasto.app.infrastructure.advice.profile.fragments.ProfileMenuFragment;
import de.robiasto.app.user.login.SecurityUserInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class ProfileMenuControllerAdvice {
    @ModelAttribute("profileMenu")
    public FragmentInterface getProfileMenu(HttpServletRequest request) {
        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal() instanceof SecurityUserInterface applicationUserDetails) {
            return new ProfileMenuFragment(
                    applicationUserDetails.getDisplayName(),
                    request.getRequestURI()
            );
        }

        return null;
    }
}
