package de.robiasto.app.infrastructure.advice.menu;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * The GlobalMainMenuItemCollector class is a utility class that allows the collection and sorting of main menu items.
 * <p>
 * Menu items can be added using the static method addMenuItem(AdviceMenuItemInterface item),
 * which adds the provided item to the list of menu items.
 * <p>
 * The sortedMenuItemList() method returns a sorted list of menu items based on their sorting value.
 * The menu items are sorted in ascending order according to the sorting value defined in the AdviceMenuItemInterface.
 * <p>
 * The MENU_ITEMS field is a static list that holds all the menu items that have been added.
 */
public class MainMenuItemCollector {

    private static final List<MainMenuItem> MENU_ITEMS = new ArrayList<>();

    private MainMenuItemCollector() {
    }

    public static void addMenuItem(MainMenuItem item) {
        MENU_ITEMS.add(item);
    }

    public static List<MainMenuItem> sortedMenuItemList() {
        return MENU_ITEMS.stream().
                         sorted(Comparator.comparing(MainMenuItem::getSorting))
                         .toList();
    }

}
