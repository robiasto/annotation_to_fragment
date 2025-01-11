package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import de.robiasto.fragment_annotation_core.interfaces.DisplayType;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * The {@code CompositionProcessor} class is responsible for processing the composition and fields in an annotation view.
 * It implements the {@code CompositionProcessorInterface} interface, which defines the methods for processing composition and fields.
 * The class is part of the fragment annotation core integration and is annotated with {@code @Component} and {@code @ComponentScan}.
 * <p>
 * The class has a constructor that takes three dependencies: a {@code ModelToFieldsMapper}, a {@code FieldsToFragmentTypes}, and a {@code TypesToAnnotationFragmentsProcessor}.
 * These dependencies are autowired using the {@code @Autowired} annotation.
 * <p>
 * The {@code getComposition} method takes an {@code AnnotationViewInterface} object as a parameter and returns an array of {@code FragmentInterface} objects representing the composition.
 * It uses the {@code modelToFields}, {@code fieldsToFragmentTypes}, and {@code typesToAnnotationFragments} dependencies to process the annotation view and retrieve the composition.
 * <p>
 * The {@code getFields} method also takes an {@code AnnotationViewInterface} object as a parameter and returns an array of {@code FieldDtoInterface} objects representing the fields.
 * It uses the {@code modelToFields} dependency to process the annotation view and retrieve the fields.
 */
@Component
@ComponentScan("de.robiasto.fragment_annotation_core.integration")
class CompositionProcessor implements CompositionProcessorInterface {
    private final ModelToFieldsMapper modelToFields;

    private final FieldsToFragmentTypes fieldsToFragmentTypes;

    private final TypesToAnnotationFragmentsProcessor typesToAnnotationFragments;

    public CompositionProcessor(
            ModelToFieldsMapper fieldMapper,
            FieldsToFragmentTypes fieldsToFragmentTypes,
            TypesToAnnotationFragmentsProcessor typesToAnnotationFragments
    ) {
        this.modelToFields = fieldMapper;
        this.fieldsToFragmentTypes = fieldsToFragmentTypes;
        this.typesToAnnotationFragments = typesToAnnotationFragments;
    }

    @Override
    public FragmentInterface[] getComposition(AnnotationViewInterface annotationViewInterface) {
        return this.fieldToFragments(this.modelToFields.process(annotationViewInterface, this, DisplayType.ALL));
    }

    @Override
    public FragmentInterface[] getComposition(AnnotationViewInterface annotationViewInterface, DisplayType displayType) {
        return this.fieldToFragments(this.modelToFields.process(annotationViewInterface, this, displayType));
    }

    @Override
    public FieldDtoInterface[] getFields(AnnotationViewInterface annotationViewInterface) {
        return this.modelToFields.process(annotationViewInterface, this, DisplayType.ALL);
    }

    private FragmentInterface[] fieldToFragments(FieldDtoInterface[] fields) {
        FragmentDtoInterface[] types = this.fieldsToFragmentTypes.process(fields);

        FragmentDtoInterface[] annotations = this.typesToAnnotationFragments.process(types);

        return Arrays.stream(annotations)
                     .map(FragmentDtoInterface::fragmentInterface)
                     .toArray(FragmentInterface[]::new);
    }
}
