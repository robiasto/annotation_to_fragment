package de.robiasto.app.user.list;

import de.robiasto.app.infrastructure.advice.menu.MainMenuItem;
import de.robiasto.app.infrastructure.advice.menu.MainMenuItemCollector;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PageableSession;
import de.robiasto.app.infrastructure.fragment.factory.view.list.ListView;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.app.user.domain.UserId;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(UserId.ROUTE_BASE_PATH)
class UsersListController {

    private final UsersListService service;
    private final PageableSession pageableSession;
    private final ListView<UserListRowView> listView;

    public UsersListController(
            UsersListService service,
            PageableSession pageableSession,
            ListView<UserListRowView> listView
    ) {
        this.service = service;
        this.pageableSession = pageableSession;
        this.listView = listView;
        MainMenuItemCollector.addMenuItem(
                new MainMenuItem("menu.users", true, RouteConfiguration.getList(new UserId()), "users", 1)
        );
    }

    @GetMapping
    public ModelAndView list(
            HttpServletRequest request,
            Pageable pageable
    ) {
        listView.initialize(
                "Users",
                RouteConfiguration.getCreate(new UserId()),
                service.getUsers(
                        this.pageableSession.getPageableFromSession("userListPageable", request, pageable)
                ).map(UserListRowView::new)
        );

        return listView.getModelAndView();
    }
}
