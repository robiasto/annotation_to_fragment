package de.robiasto.fragment_annotation_core.integration.field.sorting;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldException;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldProcessorInterface;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 *
 */
@Component
public class SortingProcessor implements FieldProcessorInterface {
    private static final Class<SortingAnnotation> META_ANNOTATION = SortingAnnotation.class;

    public FieldDtoInterface[] process(FieldDtoInterface[] fieldDtos) {
        return this.getFieldsByMetaAnnotation(Arrays.stream(fieldDtos))
                   .map(this::getAnnotationValue)
                   .sorted(this.getFieldComparator())
                   .map(fieldAndMetaAnnotationValueDto -> fieldAndMetaAnnotationValueDto.fieldDtoInterface)
                   .toArray(FieldDtoInterface[]::new);
    }

    private Stream<FieldDtoInterface> getFieldsByMetaAnnotation(Stream<FieldDtoInterface> fieldDtos) {
        return fieldDtos.filter(
                fieldDto ->
                        Arrays.stream(fieldDto.field().getAnnotations())
                              .anyMatch(
                                      annotation -> AnnotationToDtoUtility.getAnnotationDto(
                                              fieldDto.field(),
                                              META_ANNOTATION
                                      ) != null
                              )
        );
    }

    private FieldAndMetaAnnotationValueDto getAnnotationValue(
            FieldDtoInterface fieldDto
    ) {
        AnnotationToDtoUtility.AnnotationDto<SortingAnnotation> annotationDto =
                AnnotationToDtoUtility.getAnnotationDto(fieldDto.field(), META_ANNOTATION);

        Assert.notNull(annotationDto, "AnnotationDto must not be null.");

        return new FieldAndMetaAnnotationValueDto(
                fieldDto.field(),
                (int) FieldUtility.getValueByName(
                        annotationDto.annotation(),
                        annotationDto.metaAnnotation().attributeToProcess()
                ),
                fieldDto
        );
    }

    private Comparator<FieldAndMetaAnnotationValueDto> getFieldComparator() {
        return (FieldAndMetaAnnotationValueDto f1, FieldAndMetaAnnotationValueDto f2) -> {
            int sortingF1 = f1.annotationValue;
            int sortingF2 = f2.annotationValue;
            if (sortingF1 == sortingF2) {
                throw new FieldException(
                        MessageFormat.format(
                                "Equal Order value in {0} and {1}.",
                                f1.field.getName(),
                                f2.field.getName()
                        )
                );
            }

            return Integer.compare(sortingF1, sortingF2);
        };
    }

    @Override
    public int getPretence() {
        return 200;
    }

    private record FieldAndMetaAnnotationValueDto(
            Field field, int annotationValue,
            FieldDtoInterface fieldDtoInterface
    ) {
    }
}
