package de.robiasto.app.infrastructure.advice.menu.fragments;

import de.robiasto.app.infrastructure.advice.menu.MainMenuItem;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

import java.util.List;

/**
 * The MenuViewFragment class represents a fragment that displays a menu view.
 * <p>
 * The MenuViewFragment extends the AbstractFragment class, which provides methods for managing fragments.
 * <p>
 * The MenuViewFragment has the following properties:
 * - menuItemList: A list of MenuItemFragment objects representing the menu items to be displayed.
 * <p>
 * The MenuViewFragment constructor takes four parameters:
 * - fragmentPath: A string representing the path to the HTML file that contains the menu view fragment.
 * - fragmentFunction: A string representing the function name of the menu view fragment.
 * - menuItemList: A list of objects that implement the AdviceMenuItemInterface, representing the menu items to be displayed.
 * - requestUrl: A string representing the current request URL.
 * <p>
 * The MenuViewFragment class provides the following methods:
 * - getMenuItemList(): Returns the list of MenuItemFragment objects representing the menu items.
 */
public class MainMenuViewFragment extends AbstractLocalFragment {

    @Getter
    final List<MainMenuItemFragment> menuItemList;

    public MainMenuViewFragment(
            String fragmentPath,
            String fragmentFunction,
            List<MainMenuItem> menuItemList,
            String requestUrl
    ) {
        this.fragmentPath = fragmentPath;
        this.menuItemList = menuItemList
                .stream()
                .map(
                        menuItem -> new MainMenuItemFragment(
                                fragmentPath,
                                fragmentFunction,
                                menuItem,
                                requestUrl
                        )
                )
                .toList();
        this.fragmentFunction = fragmentFunction + "Menu";
    }
}
