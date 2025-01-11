package de.robiasto.fragment_annotation_core.interfaces.fragment.type.parent_element_one_level;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotation;
import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

class OneLevelRootModel implements AnnotationViewInterface {
    @PlainAnnotation(fragmentType = PlainAnnotationTypes.VIEW, sorting = 1)
    public final OneLevelParentModel parentViewDto;

    public OneLevelRootModel(String prepend) {
        this.parentViewDto = new OneLevelParentModel(prepend);
    }
}
