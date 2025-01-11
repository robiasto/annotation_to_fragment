package de.robiasto.fragment_annotation_core.fragments.annotation;

import de.robiasto.fragment_annotation_core.integration.field.sorting.SortingAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SortingAnnotation
@FragmentTypeConfiguration
public @interface PlainAnnotation {
    PlainAnnotationTypes fragmentType();

    int sorting();
}
