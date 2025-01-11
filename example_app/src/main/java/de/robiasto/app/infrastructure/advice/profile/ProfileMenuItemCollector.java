package de.robiasto.app.infrastructure.advice.profile;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProfileMenuItemCollector {
    private static final List<ProfileMenuItem> menuItems = new ArrayList<>();

    private ProfileMenuItemCollector() {
    }

    public static void addMenuItem(ProfileMenuItem item) {
        menuItems.add(item);
    }

    public static List<ProfileMenuItem> sortedMenuItemList() {
        return menuItems.stream().
                        sorted(Comparator.comparing(ProfileMenuItem::sorting))
                        .toList();
    }

}
