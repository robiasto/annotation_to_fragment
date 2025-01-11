package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractFragment;

/**
 * The FragmentColBody class extends the AbstractFragment class and represents a fragment that represents the body of a column in a composition table. It implements the FragmentInterface
 * interface.
 * <p>
 * The FragmentColBody class has the following properties:
 * - content: a FragmentInterface object that represents the content of the column body.
 * - name: a String that represents the name of the column body.
 * <p>
 * The FragmentColBody class has the following methods:
 * - Constructor: creates a new FragmentColBody object with the specified name and content.
 * - getContent(): returns the content of the column body.
 * - getName(): returns the name of the column body.
 * <p>
 * The FragmentColBody class also contains a static nested class called Mapper, which implements the FragmentAnnotationMapperInterface interface. This class is used to map a Fragment
 * DtoInterface object to a FragmentInterface object.
 */
public class FragmentColBody extends AbstractFragment {
    private final FragmentInterface content;

    private final String name;

    public FragmentColBody(
            String name,
            FragmentInterface content
    ) {
        super("fragments/composition/table/colBody.html", "colBody");
        this.name = name;
        this.content = content;
    }



    public FragmentInterface getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public static class Mapper implements FragmentAnnotationMapperInterface {
        @Override
        public FragmentInterface getFragment(FragmentDtoInterface fragmentDto) {
            return new FragmentColBody(
                    fragmentDto.field().getName(),
                    fragmentDto.fragmentInterface()
            );
        }
    }
}
