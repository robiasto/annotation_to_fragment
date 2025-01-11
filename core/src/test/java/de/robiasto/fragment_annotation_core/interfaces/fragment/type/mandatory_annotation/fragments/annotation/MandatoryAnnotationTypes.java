package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.annotation;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.type.MandatoryAnnotationTypeFragment;

public enum MandatoryAnnotationTypes implements FragmentTypeEnumInterface {
    MANDATORY_ANNOTATION(new MandatoryAnnotationTypeFragment.FragmentMapper(new StringMapper()));

    private final FragmentTypeMapperInterface fragmentMapper;

    MandatoryAnnotationTypes(FragmentTypeMapperInterface mapperClass) {
        this.fragmentMapper = mapperClass;
    }

    @Override
    public FragmentTypeMapperInterface getFragmentMapper() {
        return fragmentMapper;
    }
}
