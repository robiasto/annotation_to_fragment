package de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;

import java.lang.annotation.Annotation;

/**
 * The FragmentPostInitialMapperInterface interface represents a mapper for adding annotations to a FragmentDtoInterface object.
 * It defines a single method, addAnnotation(), that takes a FragmentDtoInterface object and an Annotation object and adds the annotation
 * to the corresponding fragment interface.
 * <p>
 * This interface is typically implemented by classes that provide specific mappings between annotations and fragment interfaces.
 * Implementations of this interface are used by the PostInitialWrapperProcessor class to process fragment DTOs and add annotations to
 * their fragment interfaces based on the meta annotation configuration.
 * <p>
 * Please refer to the documentation of the implementing classes and interfaces for specific details on usage and behavior.
 */
public interface FragmentPostInitialMapperInterface {
    void addAnnotation(FragmentDtoInterface fragmentDto, Annotation annotation);
}
