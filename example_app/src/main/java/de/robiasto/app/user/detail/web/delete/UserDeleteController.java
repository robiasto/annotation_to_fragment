package de.robiasto.app.user.detail.web.delete;

import de.robiasto.app.infrastructure.fragment.factory.page.redirect_page.RedirectModelAndView;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.infrastructure.utility.entity_helper.Security;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.domain.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(UserId.ROUTE_BASE_PATH + RouteConfiguration.DELETE)
@RequiredArgsConstructor
class UserDeleteController {

    private final UserDeleteService service;

    private final RedirectModelAndView redirectModelAndView;

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ModelAndView deleteUser(
            @PathVariable("id") UserEntity user,
            RedirectAttributes redirectAttributes
    ) {
        if (Security.isCurrentUser(user.getId())) {
            this.redirectModelAndView.setAlertError(redirectAttributes, "You can't delete yourself.");
        } else {
            service.deleteUser(user);
            this.redirectModelAndView.setAlertSuccess(redirectAttributes, "User " + user.getFirstName() + " " + user.getLastName() + " was deleted successfully.");
        }

        return this.redirectModelAndView.getRedirectModelAndView(RouteConfiguration.getList(new UserId()));
    }
}
