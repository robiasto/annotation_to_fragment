package de.robiasto.app.infrastructure.fragment.grid.fragments.fragment;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;

public class Fragment {
    private Fragment() {
    }

    public static class Mapper implements FragmentTypeMapperInterface {
        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fieldDto) {
            return (FragmentInterface) FieldUtility.getObject(fieldDto);
        }
    }
}
