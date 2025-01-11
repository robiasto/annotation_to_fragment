package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.radio_group;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select.SelectValueMapperInterface;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class RadioGroupFragment extends AbstractLocalFragment {

    protected final String name;

    protected final List<SelectValueDto> options;

    protected final boolean mandatory;

    @RequiredArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        private final SelectValueMapperInterface valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return new RadioGroupFragment(
                    fragmentDto.field().getName(),
                    this.valueMapper.getValue(fragmentDto),
                    FormFieldFragment.Mapper.getAnnotation(fragmentDto.field()).isMandatory()
            );
        }
    }
}
