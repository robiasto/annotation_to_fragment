package de.robiasto.fragment_annotation_core.interfaces.fragment.type;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;

/**
 * This interface represents a mapper for mapping different types of fields to their corresponding fragment implementations,
 * as defined by the {@link FragmentInterface} interface.
 */
public interface FragmentTypeMapperInterface {
    FragmentInterface getAnnotationMapper(FieldDtoInterface fieldDto);
}
