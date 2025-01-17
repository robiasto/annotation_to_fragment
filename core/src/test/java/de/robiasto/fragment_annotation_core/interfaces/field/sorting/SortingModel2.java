package de.robiasto.fragment_annotation_core.interfaces.field.sorting;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class SortingModel2 implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.PLAIN, sorting = 2)
    public final String name = "name";

    @PlainAnnotation(fragmentType = PlainAnnotationTypes.PLAIN, sorting = 1)
    private final String namePrivate = "namePrivate";
}
