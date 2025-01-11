package de.robiasto.app.infrastructure.fragment.grid.fragments.text;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class TextFragment extends AbstractLocalFragment {

    final String text;

    public TextFragment(String text, Template fragmentFunction) {
        this.text = text;
        this.fragmentFunction = fragmentFunction.name();
    }

    @AllArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {

        final StringMapper valueMapper;

        final Template fragmentFunction;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return new TextFragment(this.valueMapper.getValue(fragmentDto), this.fragmentFunction);
        }
    }

    public enum Template {
        TEXT_PARAGRAPH_FRAGMENT,
        TEXT_FREE_FRAGMENT
    }
}
