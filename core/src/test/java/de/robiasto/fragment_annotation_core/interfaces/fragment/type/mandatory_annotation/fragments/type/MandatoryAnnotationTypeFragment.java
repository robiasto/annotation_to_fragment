package de.robiasto.fragment_annotation_core.interfaces.fragment.type.mandatory_annotation.fragments.type;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;

import java.lang.reflect.Field;
import java.text.MessageFormat;
import java.util.Optional;

public class MandatoryAnnotationTypeFragment implements FragmentInterface {

    protected final String content;

    protected final String mandatoryText;

    public MandatoryAnnotationTypeFragment(String content, String mandatoryText) {

        this.content = content;
        this.mandatoryText = mandatoryText;
    }

    @Override
    public String getPath() {
        return "fragments/type/mandatoryAnnotationTypeFragment.html :: mandatoryAnnotationTypeFragment";
    }

    public String getContent() {
        return content;
    }

    public String getMandatoryText() {
        return mandatoryText;
    }

    public static class FragmentMapper implements FragmentTypeMapperInterface {

        private final StringMapper mapper;
        private final Class<MandatoryTypeAnnotation> mandatoryAnnotationClass = MandatoryTypeAnnotation.class;

        public FragmentMapper(StringMapper mapper) {
            this.mapper = mapper;
        }

        private String getMandatoryText(Field field) {
            return Optional.ofNullable(field.getAnnotation(this.mandatoryAnnotationClass))
                           .map(MandatoryTypeAnnotation::value)
                           .orElseThrow(() -> new MandatoryTypeException(MessageFormat.format(
                                   "@{0} is not Implemented in {1}.\n"
                                           + "Add @{0} annotation to attribute {1}.",
                                   this.mandatoryAnnotationClass.getSimpleName(),
                                   field.getName()
                           )));
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentMapperDto) {
            return new MandatoryAnnotationTypeFragment(
                    this.mapper.getValue(fragmentMapperDto),
                    this.getMandatoryText(fragmentMapperDto.field())
            );
        }
    }
}
