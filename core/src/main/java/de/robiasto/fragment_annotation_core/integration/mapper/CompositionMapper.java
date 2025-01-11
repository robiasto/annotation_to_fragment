package de.robiasto.fragment_annotation_core.integration.mapper;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;

/**
 *
 */
public class CompositionMapper {
    public FragmentInterface[] getValue(FieldDtoInterface fieldDto) {
        return fieldDto.annotationProcessor().getComposition(FieldUtility.getAnnotationView(fieldDto));
    }
}
