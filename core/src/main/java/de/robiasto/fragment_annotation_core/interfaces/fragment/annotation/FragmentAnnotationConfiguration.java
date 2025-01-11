package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to configure a mapping between an annotation and a class that implements the
 * FragmentAnnotationMapperInterface. The mapping is used to create a FragmentInterface object based on
 * a FragmentDtoInterface object.
 *
 * @see FragmentAnnotationMapperInterface
 * @see de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface
 */
@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface FragmentAnnotationConfiguration {
    Class<? extends FragmentAnnotationMapperInterface> value();
}
