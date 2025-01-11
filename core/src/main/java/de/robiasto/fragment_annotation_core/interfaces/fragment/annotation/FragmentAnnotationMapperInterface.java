package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;

/**
 * The FragmentAnnotationMapperInterface is an interface that defines a method for mapping a FragmentDtoInterface object
 * to a FragmentInterface object. This interface should be implemented by classes that provide the mapping functionality
 * for specific annotations.
 * <p>
 * The getFragment() method takes a FragmentDtoInterface object as a parameter and returns a FragmentInterface object.
 * The implementation of this method should use the information from the FragmentDtoInterface object to create and return
 * the corresponding FragmentInterface object.
 * <p>
 * It is up to the implementing classes to define the logic for mapping the FragmentDtoInterface object to the
 * FragmentInterface object.
 *
 * @see FragmentDtoInterface
 * @see FragmentInterface
 */
public interface FragmentAnnotationMapperInterface {
    FragmentInterface getFragment(FragmentDtoInterface fragmentDto);
}
