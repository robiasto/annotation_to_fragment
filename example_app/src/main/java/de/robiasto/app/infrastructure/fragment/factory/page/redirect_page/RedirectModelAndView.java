package de.robiasto.app.infrastructure.fragment.factory.page.redirect_page;

import de.robiasto.app.infrastructure.fragment.plain.alert.AlertFragment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Component
public class RedirectModelAndView {
    public ModelAndView getRedirectModelAndView(String viewName) {
        return new ModelAndView("redirect:" + viewName);
    }

    public void setAlertError(RedirectAttributes redirectAttributes, String message) {
        this.setAlertInfo(redirectAttributes, message, AlertFragment.Type.ERROR);
    }

    public void setAlertSuccess(RedirectAttributes redirectAttributes, String message) {
        this.setAlertInfo(redirectAttributes, message, AlertFragment.Type.SUCCESS);
    }

    private void setAlertInfo(RedirectAttributes redirectAttributes, String message, AlertFragment.Type type) {
        redirectAttributes.addFlashAttribute("alert", AlertFragment.withText(message, type));
    }
}
