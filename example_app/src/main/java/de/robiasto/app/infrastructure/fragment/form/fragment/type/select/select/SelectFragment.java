package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select;

import de.robiasto.app.infrastructure.fragment.form.IsSearchable;
import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class SelectFragment extends AbstractLocalFragment {
    public final String name;

    public final List<SelectValueDto> options;

    public final boolean mandatory;

    public final boolean multiple;

    public final boolean searchable;

    public final boolean placeholder;

    @RequiredArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        protected final boolean multiple;
        private final SelectValueMapperInterface valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            SelectFragment selectFragment = new SelectFragment(
                    fragmentDto.field().getName(),
                    this.valueMapper.getValue(fragmentDto),
                    FormFieldFragment.Mapper.getAnnotation(fragmentDto.field()).isMandatory(),
                    this.multiple,
                    fragmentDto.field().isAnnotationPresent(IsSearchable.class),
                    true
            );

            selectFragment.addCssClasses("plain-select");

            return selectFragment;
        }
    }
}
