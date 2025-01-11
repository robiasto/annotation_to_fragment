package de.robiasto.fragment_annotation_core.fragments.type;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;

public class Plain implements FragmentInterface {

    protected final String content;

    public Plain(String content) {
        this.content = content;
    }

    public String getPath() {
        return "fragments/type/plain.html :: plain";
    }

    public String getContent() {
        return this.content;
    }

    public static class FragmentMapper implements FragmentTypeMapperInterface {
        private final StringMapper mapper;

        public FragmentMapper(StringMapper mapper) {
            this.mapper = mapper;
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentMapperDto) {
            return new Plain(this.mapper.getValue(fragmentMapperDto));
        }
    }
}
