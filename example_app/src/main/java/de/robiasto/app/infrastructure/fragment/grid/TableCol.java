package de.robiasto.app.infrastructure.fragment.grid;

import de.robiasto.app.infrastructure.fragment.grid.fragments.table.FragmentColBody;
import de.robiasto.fragment_annotation_core.integration.field.sorting.SortingAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The TableCol annotation is used to define the configuration of a table column in a composition table.
 * It allows specifying the fragment type, sorting behavior, label, and sortable keys for the column.
 * This annotation is targeted at fields and is retained at runtime.
 *
 * @see SortingAnnotation
 * @see FragmentTypeConfiguration
 * @see FragmentAnnotationConfiguration
 * @see FragmentColBody
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SortingAnnotation
@FragmentTypeConfiguration
@FragmentAnnotationConfiguration(FragmentColBody.Mapper.class)
public @interface TableCol {
    FragmentTypes fragmentType();

    int sorting();

    String label() default "";

    boolean sortable() default false;

    String sortableKey() default "";

    String[] sortableKeys() default {};
    boolean hideOnMobile() default false;
}
