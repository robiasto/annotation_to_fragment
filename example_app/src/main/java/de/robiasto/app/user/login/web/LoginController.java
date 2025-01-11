package de.robiasto.app.user.login.web;

import de.robiasto.app.infrastructure.fragment.plain.alert.AlertFragment;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.user.infrastructure.UserId;
import de.robiasto.app.user.login.fragment.LoginView;
import de.robiasto.app.user.login.infrastructure.SecurityRoute;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    private final LoginView loginView;

    public LoginController(LoginView loginView) {
        this.loginView = loginView;
    }

    @GetMapping(SecurityRoute.LOGIN)
    public ModelAndView login(
            @AuthenticationPrincipal UserDetails userDetails,
            @RequestParam(name = "error", required = false) String error,
            @RequestParam(name = "logout", required = false) String logout,
            @RequestParam(name = "username", required = false, defaultValue = "") String username,
            @RequestParam(name = "password", required = false, defaultValue = "") String password
    ) {
        if (userDetails != null) {
            return new ModelAndView("redirect:" + RouteConfiguration.getList(new UserId()));
        }

        if (error != null) {
            this.loginView.addAlert("login.error", AlertFragment.Type.ERROR);
        }

        if (logout != null) {
            this.loginView.addAlert("login.logout.confirmation", AlertFragment.Type.SUCCESS);
        }

        this.loginView.addForm(username, password);

        return this.loginView.getModelAndView();
    }
}
