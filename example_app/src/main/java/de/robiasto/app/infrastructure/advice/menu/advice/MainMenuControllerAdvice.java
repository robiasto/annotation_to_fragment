package de.robiasto.app.infrastructure.advice.menu.advice;

import de.robiasto.app.infrastructure.advice.menu.fragments.MainMenuFragment;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
class MainMenuControllerAdvice {
    @ModelAttribute("mainMenu")
    public FragmentInterface getMainMenu(HttpServletRequest request) {
        return new MainMenuFragment(request.getRequestURI());
    }
}
