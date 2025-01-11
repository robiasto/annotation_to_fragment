package de.robiasto.app.infrastructure.advice.profile.fragments;

import de.robiasto.app.infrastructure.advice.profile.ProfileMenuItem;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

@Getter
public class ProfileMenuItemFragment extends AbstractLocalFragment {
    private final ProfileMenuItem menuItem;

    private final boolean active;

    public ProfileMenuItemFragment(
            String fragmentPath,
            String fragmentFunction,
            ProfileMenuItem menuItem,
            String requestUrl
    ) {
        this.fragmentPath = fragmentPath;
        this.fragmentFunction = fragmentFunction + "Button";
        this.active = requestUrl.startsWith(menuItem.link());
        this.menuItem = menuItem;
    }
}
