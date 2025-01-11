package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialConfiguration;
import de.robiasto.fragment_annotation_core.interfaces.fragment.post_initial.FragmentPostInitialMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.AnnotationToDtoUtility;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Arrays;

@Component
class PostInitialWrapperProcessor {
    private final MapperFactory mapperFactory;

    public PostInitialWrapperProcessor(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    public void process(
            FragmentDtoInterface[] fragmentDtos,
            ProcessType fragmentToProcess
    ) {
        Arrays.stream(fragmentDtos)
              .forEach(
                      fragmentDto ->
                              AnnotationToDtoUtility
                                      .getAnnotationDtos(
                                              fragmentDto.field(),
                                              FragmentPostInitialConfiguration.class
                                      ).forEach(
                                              annotationDto -> {
                                                  if (
                                                          annotationDto.metaAnnotation()
                                                                       .processType() != fragmentToProcess &&
                                                                  annotationDto.metaAnnotation()
                                                                               .processType() != ProcessType.ANY
                                                  ) {
                                                      return;
                                                  }

                                                  this.getMapperClass(annotationDto.metaAnnotation().mapper())
                                                      .addAnnotation(
                                                              fragmentDto,
                                                              annotationDto.annotation()
                                                      );
                                              }
                                      )
              );
    }

    private FragmentPostInitialMapperInterface getMapperClass(Class<? extends FragmentPostInitialMapperInterface> mapperClass) {
        Object mapperInstance = this.mapperFactory.getMapperInstance(mapperClass);
        Assert.isInstanceOf(
                FragmentPostInitialMapperInterface.class,
                mapperInstance,
                "Mapper class must implement IsPostInitInterface."
        );

        return (FragmentPostInitialMapperInterface) mapperInstance;
    }
}
