package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.annotation.MandatoryAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.annotation.MandatoryAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.type.MandatoryTypeAnnotation;

public class MandatoryAnnotationModel implements AnnotationViewInterface {
    @MandatoryAnnotation(fragmentType = MandatoryAnnotationTypes.MANDATORY_ANNOTATION, sorting = 1)
    @MandatoryTypeAnnotation("mandatory type value")
    public final String name1 = "name1";
}
