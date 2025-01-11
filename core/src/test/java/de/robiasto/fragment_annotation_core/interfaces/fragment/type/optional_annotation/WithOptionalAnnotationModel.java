package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.annotation.OptionalAnnotation;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.annotation.OptionalAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.type.OptionalTypeAnnotation;

public class WithOptionalAnnotationModel implements AnnotationViewInterface {
    @OptionalAnnotation(fragmentType = OptionalAnnotationTypes.WITH_OPTIONAL_ANNOTATION, sorting = 1)
    @OptionalTypeAnnotation("with annotation")
    public final String name = "attribute with annotation";
}
