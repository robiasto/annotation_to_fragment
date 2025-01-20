package de.robiasto.app.team.list;

import de.robiasto.app.infrastructure.advice.menu.MainMenuItem;
import de.robiasto.app.infrastructure.advice.menu.MainMenuItemCollector;
import de.robiasto.app.infrastructure.fragment.factory.view.list.ListView;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.team.domain.TeamId;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(TeamId.ROUTE_BASE_PATH)
class TeamListController {

    private final TeamListService service;
    private final ListView<TeamListRowView> listView;

    public TeamListController(
            TeamListService service,
            ListView<TeamListRowView> listView
    ) {
        this.service = service;
        this.listView = listView;
        MainMenuItemCollector.addMenuItem(
                new MainMenuItem("menu.teams", true, RouteConfiguration.getList(new TeamId()), "team", 1)
        );
    }

    @GetMapping
    public ModelAndView list(Pageable pageable) {
        listView.initialize(
                "Team",
                RouteConfiguration.CREATE,
                service.getTeams(pageable).map(TeamListRowView::new)
        );

        return listView.getModelAndView();
    }
}
