package de.robiasto.fragment_annotation_core.integration;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.ProcessType;
import org.springframework.stereotype.Component;

@Component
class PostInitialUtil {
    private final PostInitialWrapperProcessor postInit;

    private final PostInitialAnnotationProcessor annotationWrapper;

    public PostInitialUtil(
            PostInitialWrapperProcessor fragmentPostInitMapper,
            PostInitialAnnotationProcessor annotationWrapper
    ) {
        this.postInit = fragmentPostInitMapper;
        this.annotationWrapper = annotationWrapper;
    }

    public FragmentDtoInterface[] processFragments(FragmentDtoInterface[] fragmentDtos, ProcessType processType) {
        this.postInit.process(fragmentDtos, processType);

        return this.annotationWrapper.process(fragmentDtos, processType);
    }
}
