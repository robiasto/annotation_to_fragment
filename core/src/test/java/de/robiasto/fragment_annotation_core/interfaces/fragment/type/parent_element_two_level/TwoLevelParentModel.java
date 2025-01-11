package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_two_level;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class TwoLevelParentModel implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.VIEW, sorting = 1)
    public final TwoLevelParentParentModel name1;

    public TwoLevelParentModel(String prepend) {
        this.name1 = new TwoLevelParentParentModel(prepend);
    }
}
