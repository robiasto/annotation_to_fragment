package de.robiasto.fragment_annotation_core.integration.field.display;

import de.robiasto.fragment_annotation_core.interfaces.DisplayType;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldProcessorInterface;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 *
 */
@Component
public class DisplayProcessor implements FieldProcessorInterface {
    private static final Class<DisplayAnnotation> META_ANNOTATION = DisplayAnnotation.class;

    public FieldDtoInterface[] process(FieldDtoInterface[] fieldDtos) {
        return Arrays.stream(fieldDtos)
                     .map(this::getAnnotationValue)
                     .filter(
                             fieldAndMetaAnnotationValueDto ->
                                     fieldAndMetaAnnotationValueDto.fieldDtoInterface.displayType() == DisplayType.ALL ||
                                             fieldAndMetaAnnotationValueDto.displayType.contains(DisplayType.ALL) ||
                                             fieldAndMetaAnnotationValueDto.displayType.contains(fieldAndMetaAnnotationValueDto.fieldDtoInterface.displayType())

                     )
                     .map(fieldAndMetaAnnotationValueDto -> fieldAndMetaAnnotationValueDto.fieldDtoInterface)
                     .toArray(FieldDtoInterface[]::new);
    }

    private FieldAndMetaAnnotationValueDto getAnnotationValue(
            FieldDtoInterface fieldDto
    ) {
        if (!fieldDto.field().isAnnotationPresent(META_ANNOTATION)) {
            return new FieldAndMetaAnnotationValueDto(
                    List.of(DisplayType.ALL),
                    fieldDto
            );
        }

        AnnotationToDtoUtility.AnnotationDto<DisplayAnnotation> annotationDto = AnnotationToDtoUtility.getAnnotationDto(
                fieldDto.field(),
                META_ANNOTATION
        );

        Assert.notNull(annotationDto, "AnnotationDto must not be null.");

        return new FieldAndMetaAnnotationValueDto(
                Arrays.asList((DisplayType[]) FieldUtility.getValueByName(
                        annotationDto.annotation(),
                        annotationDto.metaAnnotation().attributeToProcess()
                )),
                fieldDto
        );
    }

    @Override
    public int getPretence() {
        return 50;
    }

    private record FieldAndMetaAnnotationValueDto(
            List<DisplayType> displayType,
            FieldDtoInterface fieldDtoInterface
    ) {
    }
}
