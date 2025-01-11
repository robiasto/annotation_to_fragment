package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.annotation;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.type.OptionalAnnotationTypeFragment;

public enum OptionalAnnotationTypes implements FragmentTypeEnumInterface {
    WITH_OPTIONAL_ANNOTATION(new OptionalAnnotationTypeFragment.FragmentMapper(new StringMapper()));

    private final FragmentTypeMapperInterface fragmentMapper;

    OptionalAnnotationTypes(FragmentTypeMapperInterface mapperClass) {
        this.fragmentMapper = mapperClass;
    }

    @Override
    public FragmentTypeMapperInterface getFragmentMapper() {
        return fragmentMapper;
    }
}
