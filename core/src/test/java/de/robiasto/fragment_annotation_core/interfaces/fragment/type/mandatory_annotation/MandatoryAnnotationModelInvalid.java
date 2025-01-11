package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.annotation.MandatoryAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.annotation.MandatoryAnnotationTypes;

public class MandatoryAnnotationModelInvalid implements AnnotationViewInterface {
    @MandatoryAnnotation(fragmentType = MandatoryAnnotationTypes.MANDATORY_ANNOTATION, sorting = 1)
    public final String name1 = "name1";
}
