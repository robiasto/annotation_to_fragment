package de.robiasto.app.infrastructure.advice.profile.fragments;

import de.robiasto.app.infrastructure.advice.profile.ProfileMenuItemCollector;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

import java.util.List;

@Getter
public class ProfileMenuFragment extends AbstractLocalFragment {

    private final String userName;

    private final List<ProfileMenuItemFragment> menuItemList;

    public ProfileMenuFragment(String userName, String requestUrl) {
        this.userName = userName;
        this.menuItemList = ProfileMenuItemCollector
                .sortedMenuItemList()
                .stream()
                .map(item -> new ProfileMenuItemFragment(
                             this.fragmentPath,
                             "profilMenu",
                             item,
                             requestUrl
                     )
                ).toList();
    }
}
