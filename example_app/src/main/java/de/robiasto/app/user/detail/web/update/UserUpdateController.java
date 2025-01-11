package de.robiasto.app.user.detail.web.update;

import de.robiasto.app.infrastructure.fragment.page.redirect_page.RedirectModelAndView;
import de.robiasto.app.infrastructure.fragment.view.form.FormView;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.infrastructure.utility.security.Security;
import de.robiasto.app.infrastructure.utility.validation.AbstractValidationController;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.service.UserDetailService;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.AllArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(UserId.ROUTE_BASE_PATH + RouteConfiguration.UPDATE)
@AllArgsConstructor
class UserUpdateController extends AbstractValidationController<UserUpdateResponse> {
    private final UserUpdateService userService;

    private final UserDetailService teamDetailService;

    private final FormView formView;

    private final RedirectModelAndView redirectModelAndView;

    private static final String LIST_ROUTE = RouteConfiguration.getList(new UserId());

    @GetMapping()
    public ModelAndView displayForm(@PathVariable("id") UserEntity user) {

        this.formView.initialize(
                "Edit User",
                new UserUpdateView(
                        user,
                        this.teamDetailService.getCareerList(user.getId()),
                        this.teamDetailService.getSelectableTeamPlayers(user.getId()),
                        this.teamDetailService.getSelectableTeamCoaches(user.getId())
                ),
                RouteConfiguration.getUpdate(user.getId()),
                LIST_ROUTE,
                Security.isAdmin()
        );

        return this.formView.getModelAndView();
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ModelAndView edit(
            @PathVariable("id") UserEntity user,
            @Validated() UserUpdateResponse userUpdateResponse,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            userService.update(user, userUpdateResponse);
            teamDetailService.updateTeam(
                    userUpdateResponse.getTeamId(),
                    user.getId(),
                    userUpdateResponse.getPosition(),
                    userUpdateResponse.isCoach()
            );

            this.redirectModelAndView.setAlertSuccess(
                    redirectAttributes,
                    "User "
                            + userUpdateResponse.getFirstName()
                            + " "
                            + userUpdateResponse.getLastName()
                            + " updated successfully."
            );


            return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);
        }

        this.redirectModelAndView.setAlertError(
                redirectAttributes,
                "User "
                        + userUpdateResponse.getLastName()
                        + " update failed."
        );

        return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);
    }
}
