package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationWrapperConfiguration;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

@Component
class PostInitialAnnotationProcessor {
    private final MapperFactory mapperFactory;

    public PostInitialAnnotationProcessor(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    public FragmentDtoInterface[] process(FragmentDtoInterface[] fragmentDtos, ProcessType type) {
        return Arrays.stream(fragmentDtos).map(
                fragmentDto -> new FieldsToFragmentTypes.FragmentDto(
                        fragmentDto,
                        this.getFragmentInterface(fragmentDto, type)
                )
        ).toArray(FragmentDtoInterface[]::new);
    }

    private FragmentInterface getFragmentInterface(FragmentDtoInterface fragmentDto, ProcessType type) {
        AnnotationToDtoUtility.AnnotationDto<FragmentAnnotationWrapperConfiguration> annotationDto =
                this.getAnnotationDto(fragmentDto);

        if (annotationDto == null || type != annotationDto.metaAnnotation().processType()) {
            return fragmentDto.fragmentInterface();
        }

        return this.getMapperClass(annotationDto.metaAnnotation().value())
                   .getFragment(fragmentDto);
    }

    private AnnotationToDtoUtility.AnnotationDto<FragmentAnnotationWrapperConfiguration> getAnnotationDto(FragmentDtoInterface processorDto) {
        return AnnotationToDtoUtility.getAnnotationDto(
                processorDto.field(),
                FragmentAnnotationWrapperConfiguration.class
        );
    }

    private FragmentAnnotationMapperInterface getMapperClass(Class<? extends FragmentAnnotationMapperInterface> mapperClass) {
        Object mapperInstance = this.mapperFactory.getMapperInstance(mapperClass);

        Assert.isInstanceOf(
                FragmentAnnotationMapperInterface.class,
                mapperInstance,
                "Mapper class must implement FragmentAnnotationMapperInterface"
        );

        return (FragmentAnnotationMapperInterface) mapperInstance;
    }
}
