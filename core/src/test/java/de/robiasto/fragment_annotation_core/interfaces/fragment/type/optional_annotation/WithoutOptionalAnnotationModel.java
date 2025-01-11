package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.annotation.OptionalAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.annotation.OptionalAnnotationTypes;

public class WithoutOptionalAnnotationModel implements AnnotationViewInterface {
    @OptionalAnnotation(fragmentType = OptionalAnnotationTypes.WITH_OPTIONAL_ANNOTATION, sorting = 1)
    public final String name = "attribute without annotation";
}
