package de.robiasto.fragment_annotation_core.interfaces.fragment.type.no_parent_element;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

public class NoParentElementModel implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.PLAIN, sorting = 1)
    public final String name1;

    public NoParentElementModel(String name1) {
        this.name1 = name1;
    }
}
