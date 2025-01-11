package de.robiasto.app.infrastructure.fragment.grid;


import de.robiasto.app.infrastructure.fragment.grid.fragments.GridColFragment;
import de.robiasto.fragment_annotation_core.integration.field.sorting.SortingAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeConfiguration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@SortingAnnotation
@FragmentTypeConfiguration
@FragmentAnnotationConfiguration(GridColFragment.Mapper.class)
public @interface GridColFragmentConfiguration {
    FragmentTypes fragmentType();

    GridColTypes gridCols() default GridColTypes.COL_6;

    GridColStartTypes gridStart() default GridColStartTypes.COL_1;

    int sorting();
}
