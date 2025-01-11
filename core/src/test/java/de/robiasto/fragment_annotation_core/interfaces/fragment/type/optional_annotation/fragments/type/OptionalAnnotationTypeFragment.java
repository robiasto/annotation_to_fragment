package de.robiasto.fragment_annotation_core.interfaces.fragment.type.optional_annotation.fragments.type;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;

import java.lang.reflect.Field;
import java.util.Optional;

public class OptionalAnnotationTypeFragment implements FragmentInterface {

    protected final String content;

    protected final String optionalText;

    public OptionalAnnotationTypeFragment(String content, String optionalText) {
        this.content = content;
        this.optionalText = optionalText;
    }

    @Override
    public String getPath() {
        return "fragments/type/optionalAnnotationTypeFragment.html :: optionalAnnotationTypeFragment";
    }

    public String getContent() {
        return content;
    }

    public String getOptionalText() {
        return optionalText;
    }

    public static class FragmentMapper implements FragmentTypeMapperInterface {

        private final StringMapper mapper;

        public FragmentMapper(StringMapper mapper) {
            this.mapper = mapper;
        }

        private String getOptionalText(Field field) {
            return Optional.ofNullable(field.getAnnotation(OptionalTypeAnnotation.class))
                           .map(OptionalTypeAnnotation::value)
                           .orElse("without annotation");
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentMapperDto) {
            return new OptionalAnnotationTypeFragment(
                    this.mapper.getValue(fragmentMapperDto),
                    this.getOptionalText(fragmentMapperDto.field())
            );
        }
    }
}
