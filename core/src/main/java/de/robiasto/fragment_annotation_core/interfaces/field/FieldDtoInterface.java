package de.robiasto.fragment_annotation_core.interfaces.field;

import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import de.robiasto.fragment_annotation_core.interfaces.DisplayType;

import java.lang.reflect.Field;

/**
 * The FieldDtoInterface is an interface that represents a data transfer object for a field. It defines three methods:
 * - `field()` returns the Field object representing the field.
 * - `model()` returns the Object representing the model.
 * - `annotationProcessor()` returns the CompositionProcessorInterface object representing the annotation processor.
 * <p>
 * This interface is used by other classes, such as FragmentDtoInterface and FieldProcessorInterface, to handle field data. It provides a standardized way to access the field, model
 * , and annotation processor associated with a field.
 * <p>
 * Please refer to the documentation of the implementing classes and interfaces for more details on usage.
 */
public interface FieldDtoInterface {
    Field field();

    Object model();

    CompositionProcessorInterface annotationProcessor();

    DisplayType displayType();
}
