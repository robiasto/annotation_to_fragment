package de.robiasto.fragment_annotation_core.interfaces;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;

/**
 * The CompositionProcessorInterface is an interface that defines the methods for processing composition and fields in an annotation view.
 * It includes the following methods:
 * - `getComposition` takes an AnnotationViewInterface object as a parameter and returns an array of FragmentInterface objects representing the composition.
 * - `getFields` takes an AnnotationViewInterface object as a parameter and returns an array of FieldDtoInterface objects representing the fields.
 * <p>
 * This interface is used by the CompositionProcessor class, which implements it and provides the necessary implementation for the methods.
 * The CompositionProcessor class is responsible for mapping models to fields, fields to fragment types, and fragment types to annotation fragments.
 * It is part of the fragment annotation core integration and is annotated with `@Component` and `@ComponentScan`.
 * The class has a constructor that takes three dependencies: a ModelToFieldsMapper, a FieldsToFragmentTypes, and a TypesToAnnotationFragmentsProcessor.
 * <p>
 * The CompositionProcessor class overrides the `getComposition` and `getFields` methods from the CompositionProcessorInterface interface.
 * The `getComposition` method uses the modelToFields, fieldsToFragmentTypes, and typesToAnnotationFragments dependencies to process the annotation view and return an array of Fragment
 * Interface objects representing the composition.
 * The `getFields` method uses the modelToFields dependency to process the annotation view and return an array of FieldDtoInterface objects representing the fields.
 * <p>
 * The CompositionProcessor class also includes other methods and nested classes that are not part of the CompositionProcessorInterface interface.
 * These methods and classes are used internally by the CompositionProcessor class for processing the annotation view and its components.
 * They are not relevant to the usage of the CompositionProcessorInterface interface.
 */
public interface CompositionProcessorInterface {

    /**
     * Retrieves the composition of a given AnnotationViewInterface object.
     * <p>
     *
     * @param models the AnnotationViewInterface object for which to retrieve the composition
     * @return an array of FragmentInterface objects representing the composition
     */
    FragmentInterface[] getComposition(AnnotationViewInterface models);


    /**
     * Retrieves the composition of a given AnnotationViewInterface object.
     * <p>
     *
     * @param models the AnnotationViewInterface object for which to retrieve the composition
     * @return an array of FragmentInterface objects representing the composition
     */
    FragmentInterface[] getComposition(AnnotationViewInterface models, DisplayType displayType);

    /**
     * Retrieves the fields in an AnnotationViewInterface object.
     * <p>
     *
     * @param model the AnnotationViewInterface object for which to retrieve the fields
     * @return an array of FieldDtoInterface objects representing the fields
     */
    FieldDtoInterface[] getFields(AnnotationViewInterface model);
}
