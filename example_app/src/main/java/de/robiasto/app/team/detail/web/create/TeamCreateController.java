package de.robiasto.app.team.detail.web.create;

import de.robiasto.app.infrastructure.fragment.page.redirect_page.RedirectModelAndView;
import de.robiasto.app.infrastructure.fragment.view.form.FormView;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.infrastructure.utility.security.Security;
import de.robiasto.app.infrastructure.utility.validation.AbstractValidationController;
import de.robiasto.app.team.detail.domain.TeamEntity;
import de.robiasto.app.team.detail.service.TeamFormView;
import de.robiasto.app.team.detail.service.TeamMemberService;
import de.robiasto.app.team.detail.service.TeamResponse;
import de.robiasto.app.team.infrastructure.TeamId;
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
@RequestMapping(TeamId.ROUTE_BASE_PATH + RouteConfiguration.CREATE)
@AllArgsConstructor
class TeamCreateController extends AbstractValidationController<TeamResponse> {
    private final TeamCreateService createService;

    private final TeamMemberService teamMemberService;

    private final FormView formView;

    private final RedirectModelAndView redirectModelAndView;

    private static final String LIST_ROUTE = RouteConfiguration.getList(new TeamId());

    @GetMapping()
    public ModelAndView displayForm(@PathVariable("id") TeamEntity team) {
        this.formView.initialize(
                "Create Team",
                new TeamFormView(
                        createService.getCoaches(team.getId()),
                        createService.getPlayers(team.getId())
                ),
                RouteConfiguration.getUpdate(team.getId()),
                LIST_ROUTE,
                Security.isAdmin()
        );

        return this.formView.getModelAndView();
    }

    @PostMapping()
    @Secured("ROLE_ADMIN")
    public ModelAndView edit(
            @PathVariable("id") TeamEntity team,
            @Validated() TeamResponse formData,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {
        if (!bindingResult.hasErrors()) {
            createService.update(team, formData);
            teamMemberService.updateTeamMember(team, formData);
            this.redirectModelAndView.setAlertSuccess(redirectAttributes, "Team " + formData.getName() + " updated successfully.");

            return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);
        }

        this.redirectModelAndView.setAlertError(redirectAttributes, "Team " + formData.getName() + " update failed.");

        return this.redirectModelAndView.getRedirectModelAndView(LIST_ROUTE);
    }
}
