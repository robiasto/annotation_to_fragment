package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.CompositionProcessorInterface;
import de.robiasto.fragment_annotation_core.interfaces.DisplayType;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldException;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

@Component
class FieldsToFragmentTypes {
    private final PostInitialUtil postInitialUtil;

    public FieldsToFragmentTypes(PostInitialUtil postInitialUtil) {
        this.postInitialUtil = postInitialUtil;
    }

    public FragmentDtoInterface[] process(FieldDtoInterface[] fields) {
        FragmentDtoInterface[] fragmentDtos = Arrays
                .stream(fields)
                .map(fieldDto -> new FragmentDto(fieldDto, this.getMapperClass(fieldDto).getAnnotationMapper(fieldDto)))
                .toArray(FragmentDto[]::new);

        return this.postInitialUtil.processFragments(fragmentDtos, ProcessType.TYPE);
    }

    private FragmentTypeMapperInterface getMapperClass(FieldDtoInterface fieldDto) {
        AnnotationToDtoUtility.AnnotationDto<FragmentTypeConfiguration> annotationDto =
                Optional.ofNullable(AnnotationToDtoUtility.getAnnotationDto(fieldDto.field(), FragmentTypeConfiguration.class))
                        .orElseThrow(() -> new FieldException(
                                MessageFormat.format(
                                        "@{0} is not Implemented in {1}.\n"
                                                + "Add @{0} annotation to attribute {1}.",
                                        FragmentTypeConfiguration.class.getSimpleName(),
                                        fieldDto.field().getName()
                                )));

        Object fragmentType = FieldUtility.getValueByName(
                annotationDto.annotation(),
                annotationDto.metaAnnotation().attributeToMap()
        );

        Assert.isInstanceOf(
                FragmentTypeEnumInterface.class,
                fragmentType,
                "Mapper class must be a instance of FieldsToFragmentEnumTypeInterface."
        );

        return ((FragmentTypeEnumInterface) fragmentType).getFragmentMapper();
    }

    record FragmentDto(
            Field field,
            Object model,
            CompositionProcessorInterface annotationProcessor,
            FragmentInterface fragmentInterface,
            DisplayType displayType
    ) implements FragmentDtoInterface {
        public FragmentDto(FieldDtoInterface fieldDtoInterface, FragmentInterface fragmentInterface) {
            this(fieldDtoInterface.field(), fieldDtoInterface.model(), fieldDtoInterface.annotationProcessor(), fragmentInterface, fieldDtoInterface.displayType());
        }
    }
}
