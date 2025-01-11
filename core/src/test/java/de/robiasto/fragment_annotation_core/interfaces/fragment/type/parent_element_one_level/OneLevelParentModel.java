package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_one_level;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class OneLevelParentModel implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.PLAIN, sorting = 1)
    public final String name1;

    public OneLevelParentModel(String prepend) {
        this.name1 = prepend + " parent view";
    }
}
