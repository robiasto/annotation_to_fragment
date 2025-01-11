package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import de.robiasto.fragment_annotation_core.interfaces.DisplayType;
import de.robiasto.fragment_annotation_core.interfaces.ExtendedViewInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldProcessorInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
class ModelToFieldsMapper {
    private final List<FieldProcessorInterface> fieldProcessors;

    public ModelToFieldsMapper(
            List<FieldProcessorInterface> fieldProcessorInterfaces
    ) {
        this.fieldProcessors = fieldProcessorInterfaces;
    }

    public FieldDtoInterface[] process(AnnotationViewInterface model, CompositionProcessorInterface compositionProcessor, DisplayType displayType) {
        AtomicReference<FieldDtoInterface[]> fields = new AtomicReference<>(getFields(model, compositionProcessor, displayType));

        (this.fieldProcessors.stream().sorted(Comparator.comparingInt(FieldProcessorInterface::getPretence)))
                .forEach(fieldProcessorInterface -> fields.set(fieldProcessorInterface.process(fields.get())));

        return fields.get();
    }

    private FieldDtoInterface[] getFields(AnnotationViewInterface model, CompositionProcessorInterface compositionProcessor, DisplayType displayType){
        List<FieldDto> fieldDtoList = new ArrayList<>(
                FieldUtility
                        .getFieldsFromModel(model)
                        .map(field -> new FieldDto(field, model, compositionProcessor, displayType))
                        .toList());

        if(model instanceof ExtendedViewInterface extendedViewInterface) {

            extendedViewInterface.getAddedViews().forEach(annotationViewInterface ->
                fieldDtoList.addAll(
                        FieldUtility
                                .getFieldsFromModel(annotationViewInterface)
                                .map(field -> new FieldDto(field, annotationViewInterface, compositionProcessor, displayType))
                                .toList()
                )
            );
        }

        return fieldDtoList.toArray(FieldDtoInterface[]::new);
    }

    record FieldDto(
            Field field,
            Object model,
            CompositionProcessorInterface annotationProcessor,
            DisplayType displayType
    ) implements FieldDtoInterface {
    }
}
