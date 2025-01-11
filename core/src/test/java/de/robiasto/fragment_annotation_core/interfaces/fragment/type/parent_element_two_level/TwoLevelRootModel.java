package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_two_level;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class TwoLevelRootModel implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.VIEW, sorting = 1)
    public final TwoLevelParentModel parentViewDto;

    public TwoLevelRootModel(String prepend) {
        this.parentViewDto = new TwoLevelParentModel(prepend);
    }
}
