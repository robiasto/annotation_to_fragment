package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.annotation.FragmentAnnotationMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

@Component
class TypesToAnnotationFragmentsProcessor {

    private final PostInitialUtil postInitialUtil;
    private final MapperFactory mapperFactory;

    public TypesToAnnotationFragmentsProcessor(
            PostInitialUtil postInitialUtil,
            MapperFactory mapperFactory
    ) {
        this.postInitialUtil = postInitialUtil;
        this.mapperFactory = mapperFactory;
    }

    public FragmentDtoInterface[] process(FragmentDtoInterface[] fragmentDtos) {
        FragmentDtoInterface[] fragments = Arrays.stream(fragmentDtos).map(
                fragmentDto -> new FieldsToFragmentTypes
                        .FragmentDto(fragmentDto, this.getFragmentInterface(fragmentDto))
        ).toArray(FieldsToFragmentTypes.FragmentDto[]::new);

        return this.postInitialUtil.processFragments(fragments, ProcessType.ANNOTATION);
    }

    private FragmentInterface getFragmentInterface(FragmentDtoInterface fragmentDto) {
        AnnotationToDtoUtility.AnnotationDto<FragmentAnnotationConfiguration> annotationDto =
                this.getAnnotationDto(fragmentDto);

        if (annotationDto == null) {
            return fragmentDto.fragmentInterface();
        }

        return this.getMapperClass(annotationDto.metaAnnotation().value())
                   .getFragment(fragmentDto);
    }

    private AnnotationToDtoUtility.AnnotationDto<FragmentAnnotationConfiguration> getAnnotationDto(FragmentDtoInterface processorDto) {
        return AnnotationToDtoUtility.getAnnotationDto(
                processorDto.field(),
                FragmentAnnotationConfiguration.class
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
