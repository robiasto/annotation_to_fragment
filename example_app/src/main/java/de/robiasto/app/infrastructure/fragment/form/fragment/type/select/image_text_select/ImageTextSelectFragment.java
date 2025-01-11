package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select;

import de.robiasto.app.infrastructure.fragment.form.IsSearchable;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select.SelectFragment;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ImageTextSelectFragment extends AbstractLocalFragment {
    public final String name;
    public final SelectFragment selectFragment;
    public final List<ImageTextOptionFragment> options;
    public final boolean multiple;


    @RequiredArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        protected final boolean multiple;
        private final ImageTextSelectValueMapper imageValueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            String name = fragmentDto.field().getName();
            return new ImageTextSelectFragment(
                    name,
                    this.getSelectFragment(fragmentDto),
                    this.imageValueMapper.getOptionFragments(fragmentDto, name, this.multiple),
                    this.multiple
            );
        }

        private SelectFragment getSelectFragment(FieldDtoInterface fragmentDto) {
            SelectFragment selectFragment = new SelectFragment(
                    fragmentDto.field().getName(),
                    this.imageValueMapper.getSelectedValue(fragmentDto),
                    FormFieldFragment.Mapper.getAnnotation(fragmentDto.field()).isMandatory(),
                    this.multiple,
                    fragmentDto.field().isAnnotationPresent(IsSearchable.class),
                    true
            );

            selectFragment.addCssClasses("image-text-select " + (this.multiple ? "multiple" : "single"));

            return selectFragment;
        }
    }
}
