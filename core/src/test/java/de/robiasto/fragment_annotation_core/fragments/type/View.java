package de.robiasto.fragment_annotation_core.fragments.type;

import de.robiasto.fragment_annotation_core.integration.mapper.CompositionMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;

public class View implements FragmentInterface {

    protected final FragmentInterface[] viewFragments;

    public View(FragmentInterface[] viewFragments) {
        this.viewFragments = viewFragments;
    }

    @Override
    public String getPath() {
        return "fragments/type/view.html :: view";
    }

    public FragmentInterface[] getViewFragments() {
        return this.viewFragments;
    }

    public static class FragmentMapper implements FragmentTypeMapperInterface {

        private final CompositionMapper compositionMapper;

        public FragmentMapper(CompositionMapper compositionMapper) {
            this.compositionMapper = compositionMapper;
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentMapperDto) {
            return new View(this.compositionMapper.getValue(fragmentMapperDto));
        }
    }
}
