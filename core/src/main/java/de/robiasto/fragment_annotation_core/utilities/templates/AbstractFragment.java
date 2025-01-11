package de.robiasto.fragment_annotation_core.utilities.templates;

import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.CssClassFragmentInterface;
import de.robiasto.fragment_annotation_core.annotation.post_init.set_fragment_path.SetFragmentPathInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * The `AbstractFragment` class is an abstract base class that represents a fragment in an application.
 * Fragments are reusable UI components that can be styled using CSS classes.
 * This class implements the `CssClassFragmentInterface`, `FragmentInterface`, and `SetFragmentPathInterface` interfaces.
 * It provides common functionality and properties for all fragments in the application.
 * <p>
 * Properties:
 * - `cssClassesToAdd`: A string that contains the CSS classes to be added to the fragment.
 * - `path`: A string that represents the path of the fragment.
 * - `fragmentFunction`: A string that represents the function of the fragment.
 * <p>
 * Constructor:
 * - `AbstractFragment(String path, String fragmentFunction)`: Creates a new instance of the `AbstractFragment` class with the specified path and fragment function.
 * <p>
 * Methods:
 * - `getCssClassesToAdd()`: Returns the CSS classes to be added to the fragment.
 * - `addCssClasses(String cssClasses)`: Adds the specified CSS classes to the fragment.
 * - `getPath()`: Returns the path of the fragment.
 * - `setPath(String path)`: Sets the path of the fragment.
 * <p>
 * This class is intended to be extended by specific fragment classes in the application.
 * <p>
 * Implementations:
 * - `LinkFragment`: Represents a fragment that displays a link.
 * - `ProfileMenuFragment`: Represents a fragment that displays a profile menu.
 * - `SelectFragment`: Represents a fragment that displays a select form field.
 * - `HeadlineFragment`: Represents a fragment that displays a headline.
 * - `PaginationFragment`: Represents a fragment that displays pagination controls.
 * <p>
 * Implementing Interfaces:
 * - `CssClassFragmentInterface`: Allows fragments to dynamically add CSS classes to themselves.
 * - `FragmentInterface`: Represents a fragment in the application.
 * - `SetFragmentPathInterface`: Allows fragments to set their own paths.
 * <p>
 * Note: This class should not be instantiated directly, as it is abstract.
 * Instead, use one of the concrete implementation classes mentioned above.
 */
@Getter
public abstract class AbstractFragment implements CssClassFragmentInterface, FragmentInterface, SetFragmentPathInterface {
    private String cssClassesToAdd = "";

    @Setter
    protected String path;

    @Setter
    protected String fragmentFunction;

    protected AbstractFragment(String path, String fragmentFunction) {
        this.fragmentFunction = fragmentFunction;
        this.path = path;
    }

    protected AbstractFragment() {
        this.fragmentFunction = this.getClass().getSimpleName();
        this.path = "fragments/" + this.getClass().getPackage().getName().replace('.', '/') + '/' + fragmentFunction + ".html";
    }

    public void setPath(String path, String fragmentFunction) {
        this.path = path + " :: " + fragmentFunction;
    }

    public String getPath() {
        return this.path + " :: " + this.fragmentFunction;
    }

    public void addCssClasses(String cssClassesToAdd) {
        this.cssClassesToAdd += (Objects.equals(this.cssClassesToAdd, "") ? "" : " ") + cssClassesToAdd;
    }

}
