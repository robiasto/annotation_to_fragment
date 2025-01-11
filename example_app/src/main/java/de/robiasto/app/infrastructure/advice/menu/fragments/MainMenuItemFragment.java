package de.robiasto.app.infrastructure.advice.menu.fragments;

import de.robiasto.app.infrastructure.advice.menu.MainMenuItem;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

/**
 * The MenuItemFragment class represents a fragment that displays a menu item.
 * <p>
 * The MenuItemFragment extends the AbstractFragment class, which provides methods for managing fragments.
 * <p>
 * The MenuItemFragment has the following properties:
 * - menuItem: A reference to an object that implements the AdviceMenuItemInterface, representing the menu item to be displayed.
 * - active: A boolean value indicating whether the menu item is currently active.
 * <p>
 * The MenuItemFragment constructor takes four parameters:
 * - fragmentPath: A string representing the path to the HTML file that contains the menu item.
 * - fragmentFunction: A string representing the function name of the menu item.
 * - menuItem: An object that implements the AdviceMenuItemInterface, representing the menu item to be displayed.
 * - requestUrl: A string representing the current request URL.
 * <p>
 * The MenuItemFragment class provides the following methods:
 * - getMenuItem(): Returns the menu item object.
 * - isActive(): Returns a boolean value indicating whether the menu item is active.
 */
public class MainMenuItemFragment extends AbstractLocalFragment {
    @Getter
    private final MainMenuItem menuItem;

    @Getter
    private final boolean active;

    public MainMenuItemFragment(
            String fragmentPath,
            String fragmentFunction,
            MainMenuItem menuItem,
            String requestUrl
    ) {
        this.fragmentFunction = fragmentFunction + "Button";
        this.fragmentPath = fragmentPath;
        this.active = requestUrl.startsWith(menuItem.getLink());
        this.menuItem = menuItem;
    }
}
