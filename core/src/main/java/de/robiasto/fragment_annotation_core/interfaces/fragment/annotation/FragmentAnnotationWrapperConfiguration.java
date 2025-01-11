package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * FragmentAnnotationWrapperConfiguration is an annotation type used for configuring the mapping of a FragmentDtoInterface object to a FragmentInterface object.
 * <p>
 * It has two elements:
 * - value (Class<? extends FragmentAnnotationMapperInterface>): Specifies the class that implements the mapping logic for the annotation.
 * - processType (ProcessType): Specifies the type of processing to be performed. It has a default value of ProcessType.ANY.
 * <p>
 * This annotation should be applied to other annotations that require the mapping functionality provided by the specified mapper class.
 *
 * @see FragmentAnnotationMapperInterface
 * @see ProcessType
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentAnnotationWrapperConfiguration {
    Class<? extends FragmentAnnotationMapperInterface> value();

    ProcessType processType() default ProcessType.ANY;
}
