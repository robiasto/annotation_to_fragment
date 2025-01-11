package de.robiasto.app.user.detail.web.create;

import de.robiasto.app.infrastructure.fragment.page.redirect_page.RedirectModelAndView;
import de.robiasto.app.infrastructure.fragment.view.form.FormView;
import de.robiasto.app.infrastructure.utility.file.FileUtility;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.infrastructure.utility.validation.AbstractValidationController;
import de.robiasto.app.user.detail.domain.UserEntity;
import de.robiasto.app.user.detail.service.UserDetailService;
import de.robiasto.app.user.infrastructure.UserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(UserId.ROUTE_BASE_PATH + RouteConfiguration.CREATE)
@RequiredArgsConstructor
class UserCreateController extends AbstractValidationController<UserCreateResponse> {
    private final FormView formView;

    private final RedirectModelAndView redirectModelAndView;

    private final UserCreateService service;

    private final UserDetailService userDetailService;

    private final FileUtility fileUtility;

    private static final String LIST_ROUTE = RouteConfiguration.getList(new UserId());

    @GetMapping()
    @Secured("ROLE_ADMIN")
    public ModelAndView display() {
        this.formView.initialize(
                "Create User",
                new UserCreateView(
                        this.userDetailService.getSelectableTeamPlayers(),
                        this.userDetailService.getSelectableTeamCoaches()
                ),
                RouteConfiguration.getCreate(new UserId()),
                LIST_ROUTE,
                true
        );

        return this.formView.getModelAndView();
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ModelAndView create(
            @Validated() @ModelAttribute("user") UserCreateResponse formData,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            UserEntity user = service.createUser(formData);
            this.redirectModelAndView.setAlertSuccess(redirectAttributes, "User " + formData.getLastName() + " creation successfully.");

            if(formData.getAvatar() != null) {
                fileUtility.saveFile(formData.getAvatar(), RouteConfiguration.getAvatar(user.getId()));
            }

            return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);
        }

        this.redirectModelAndView.setAlertError(redirectAttributes, "User " + formData.getLastName() + " creation failed.");

        return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);

    }
}
