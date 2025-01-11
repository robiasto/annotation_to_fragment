package de.robiasto.fragment_annotation_core.fragments.annotation;


import de.robiasto.fragment_annotation_core.fragments.type.Fragment;
import de.robiasto.fragment_annotation_core.fragments.type.Plain;
import de.robiasto.fragment_annotation_core.fragments.type.View;
import de.robiasto.fragment_annotation_core.integration.mapper.CompositionMapper;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;

public enum PlainAnnotationTypes implements FragmentTypeEnumInterface {
    PLAIN(new Plain.FragmentMapper(new StringMapper())),
    VIEW(new View.FragmentMapper(new CompositionMapper())),
    FRAGMENT(new Fragment.FragmentMapper());

    private final FragmentTypeMapperInterface fragmentMapper;

    PlainAnnotationTypes(FragmentTypeMapperInterface mapperClass) {
        this.fragmentMapper = mapperClass;
    }

    @Override
    public FragmentTypeMapperInterface getFragmentMapper() {
        return this.fragmentMapper;
    }
}
