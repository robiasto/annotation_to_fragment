package de.robiasto.app.infrastructure.advice.menu.fragments;

import de.robiasto.app.infrastructure.advice.menu.MainMenuItem;
import de.robiasto.app.infrastructure.advice.menu.MainMenuItemCollector;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

import java.util.List;

/**
 * The MainMenuFragment class represents a fragment that displays the main menu of a web application. It extends the AbstractFragment class and implements the FragmentInterface, Css
 * ClassFragmentInterface, and SetFragmentPathInterface interfaces.
 * <p>
 * The MainMenuFragment contains two instances of the MenuViewFragment class: one for mobile devices and one for desktop devices. Both instances are created during the construction
 * of the MainMenuFragment.
 * <p>
 * The mainMenu.html file is used as the fragment path for both MenuViewFragment instances. The fragment function names are "mobile" and "desktop" respectively. The requestUrl parameter
 * passed to the constructor is used to generate the menu items with the matching links.
 * <p>
 * The getMenuMobile() method returns the mobile MenuViewFragment instance.
 * <p>
 * The getMenuDesktop() method returns the desktop MenuViewFragment instance.
 */
public class MainMenuFragment extends AbstractLocalFragment {
    @Getter
    private final MainMenuViewFragment mobile;

    @Getter
    private final MainMenuViewFragment desktop;

    public MainMenuFragment(String requestUrl) {
        List<MainMenuItem> menuItems = MainMenuItemCollector.sortedMenuItemList();
        this.mobile = new MainMenuViewFragment(this.fragmentPath, "mobile", menuItems, requestUrl);
        this.desktop = new MainMenuViewFragment(this.fragmentPath, "desktop", menuItems, requestUrl);
    }
}
