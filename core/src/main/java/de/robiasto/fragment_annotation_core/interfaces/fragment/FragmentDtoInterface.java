package de.robiasto.fragment_annotation_core.interfaces.fragment;

import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;

import java.lang.reflect.Field;

/**
 * The FragmentDtoInterface interface extends the FieldDtoInterface interface and represents a data transfer object for a fragment. It defines four methods:
 * - `field()` returns the Field object representing the fragment's field.
 * - `model()` returns the Object representing the fragment's model.
 * - `annotationProcessor()` returns the CompositionProcessorInterface object representing the annotation processor associated with the fragment.
 * - `fragmentInterface()` returns the FragmentInterface object representing the fragment.
 * <p>
 * This interface is used to handle fragment data and provides a standardized way to access the fragment's field, model, annotation processor, and fragment interface.
 * <p>
 * Please refer to the documentation of the implementing classes and interfaces for more details on usage.
 */
public interface FragmentDtoInterface extends FieldDtoInterface {
    Field field();

    Object model();

    CompositionProcessorInterface annotationProcessor();

    FragmentInterface fragmentInterface();
}
