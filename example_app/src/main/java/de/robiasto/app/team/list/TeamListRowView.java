package de.robiasto.app.team.list;

import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.LinkConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.TableCol;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.AddCssClassOnAnnotation;
import de.robiasto.fragment_annotation_core.integration.field.authorize.AuthorizeFragment;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class TeamListRowView implements AnnotationViewInterface {
    @TableCol(
            fragmentType = FragmentTypes.TEXT_FREE,
            sorting = 1,
            label = "user.name",
            sortable = true,
            sortableKeys = {"name"}
    )
    @AddCssClassOnAnnotation("font-medium text-gray-900")
    final String name;

    @TableCol(fragmentType = FragmentTypes.LINK, sorting = 2)
    @LinkConfiguration(translation = "list.edit")
    @AuthorizeFragment({"ROLE_ADMIN"})
    final String edit;

    @TableCol(fragmentType = FragmentTypes.LINK, sorting = 3)
    @LinkConfiguration(translation = "list.detail")
    @AuthorizeFragment({"ROLE_USER"})
    final String show;

    public TeamListRowView(TeamListEntity team) {
        this.name = team.getName();
        this.edit = RouteConfiguration.getUpdate(team.getId());
        this.show = RouteConfiguration.getUpdate(team.getId());
    }
}
