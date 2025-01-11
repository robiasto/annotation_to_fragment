package de.robiasto.app.user.list;

import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.LinkConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.TableCol;
import de.robiasto.app.infrastructure.utility.id.RouteConfiguration;
import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.AddCssClassOnAnnotation;
import de.robiasto.fragment_annotation_core.integration.field.authorize.AuthorizeFragment;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class UserListRowView implements AnnotationViewInterface {
    @TableCol(
            fragmentType = FragmentTypes.TEXT_FREE,
            sorting = 1,
            label = "user.name",
            sortable = true,
            sortableKeys = {"firstName", "lastName"}
    )
    @AddCssClassOnAnnotation("font-medium text-gray-900")
    final String name;

    @TableCol(
            fragmentType = FragmentTypes.TEXT_FREE,
            sorting = 2,
            label = "user.gender",
            sortable = true,
            hideOnMobile = true
    )
    final String gender;

    @TableCol(
            fragmentType = FragmentTypes.TEXT_FREE,
            sorting = 3,
            label = "user.birthday",
            sortable = true,
            sortableKeys = {"birthday"},
            hideOnMobile = true
    )
    final String birthday;

    @TableCol(
            fragmentType = FragmentTypes.TEXT_FREE,
            sorting = 4,
            label = "user.email",
            sortable = true,
            sortableKeys = {"email"},
            hideOnMobile = true
    )
    final String email;

    @TableCol(fragmentType = FragmentTypes.LINK, sorting = 6)
    @LinkConfiguration(text = "Delete", type = LinkType.FORM_CONFIRM)
    @AuthorizeFragment("ROLE_ADMIN")
    final String delete;

    @TableCol(fragmentType = FragmentTypes.LINK, sorting = 5)
    @LinkConfiguration(translation = "list.edit")
    @AuthorizeFragment({"ROLE_ADMIN"})
    final String edit;

    @TableCol(fragmentType = FragmentTypes.LINK, sorting = 7)
    @LinkConfiguration(translation = "list.detail")
    @AuthorizeFragment({"ROLE_USER"})
    final String detail;

    public UserListRowView(ListUserEntity user) {
        this.name = user.getDisplayName();
        this.birthday = user.getBirthday().toString();
        this.gender = user.getGender().toString();
        this.email = user.getEmail();
        this.delete = RouteConfiguration.getDelete(user.getId());
        this.edit = RouteConfiguration.getUpdate(user.getId());
        this.detail = RouteConfiguration.getUpdate(user.getId());
    }
}
