package de.robiasto.fragment_annotation_core.interfaces.fragment.annotation;

import de.robiasto.fragment_annotation_core.fragments.annotation.PlainAnnotationTypes;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.fragments.WithHeadlineAnnotation;

public class FragmentAnnotationModel implements AnnotationViewInterface {
    @WithHeadlineAnnotation(
            fragmentType = PlainAnnotationTypes.PLAIN,
            sorting = 1,
            headlineText = "Headline Text"
    )
    private final String name = "name";
}
