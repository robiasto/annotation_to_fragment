package de.robiasto.app.infrastructure.fragment.grid.fragments.headline;

import de.robiasto.app.infrastructure.fragment.grid.HeadlineConfiguration;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@Getter
public class HeadlineFragment extends AbstractLocalFragment {
    protected final String content;

    protected final Type type;

    public HeadlineFragment(String content, Type type) {
        this.content = content;
        this.type = type;
        this.addCssClasses(type.cssClass);
    }


    public enum Type {
        H1("text-2xl font-bold"),
        H2("text-2xl font-medium"),
        H3("text-2xl font-light"),
        H4("text-1xl font-bold"),
        H5("text-1xl font-medium"),
        H6("text-1xl font-light");

        private final String cssClass;

        Type(String cssClass) {
            this.cssClass = cssClass;
        }
    }

    @AllArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        private final StringMapper valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return new HeadlineFragment(
                    this.valueMapper.getValue(fragmentDto),
                    Optional
                            .ofNullable(fragmentDto.field().getAnnotation(HeadlineConfiguration.class))
                            .map(HeadlineConfiguration::value)
                            .orElse(Type.H1)
            );
        }
    }

    @AllArgsConstructor
    public static class FormMapper implements FragmentTypeMapperInterface {
        private final StringMapper valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            HeadlineFragment headlineFragment = new HeadlineFragment(
                    this.valueMapper.getValue(fragmentDto),
                    Type.H3
            );

            headlineFragment.addCssClasses("border-b-[1px] border-line-[1px] pb-1");

            return headlineFragment;
        }
    }
}
