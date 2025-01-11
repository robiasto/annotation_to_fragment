package de.robiasto.app.infrastructure.fragment.grid.fragments;


import de.robiasto.app.infrastructure.fragment.grid.GridColFragmentConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Optional;


@Getter
public class GridColFragment extends AbstractLocalFragment {
    private final FragmentInterface fragment;

    protected GridColFragment(FragmentInterface fragment, String colType, String cotStart) {
        this.fragment = fragment;
        this.addCssClasses(colType);
        this.addCssClasses(cotStart);
    }

    public static class Mapper implements FragmentAnnotationMapperInterface {

        @Override
        public FragmentInterface getFragment(FragmentDtoInterface fragmentDto) {
            GridColFragmentConfiguration annotation = this.getAnnotation(fragmentDto.field());

            return new GridColFragment(
                    fragmentDto.fragmentInterface(),
                    annotation.gridCols().getCssClass(),
                    annotation.gridStart().getCssClass()
            );
        }

        public GridColFragmentConfiguration getAnnotation(Field field) {
            return Optional.ofNullable(field.getAnnotation(GridColFragmentConfiguration.class))
                           .orElseThrow(() -> new MissingGridTypConfigurationException(
                                   MessageFormat.format(
                                           "@{0} is not Implemented in {1}.\n"
                                                   + "Add @{0} annotation to attribute {1}.",
                                           GridColFragmentConfiguration.class.getSimpleName(),
                                           field.getName()
                                   )));
        }
    }
}
