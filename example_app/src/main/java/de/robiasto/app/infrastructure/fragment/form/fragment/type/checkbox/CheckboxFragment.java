package de.robiasto.app.infrastructure.fragment.form.fragment.type.checkbox;

import de.robiasto.app.infrastructure.fragment.form.FormField;
import de.robiasto.app.infrastructure.fragment.form.fragment.FormFieldFragment;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import org.springframework.util.Assert;

public class CheckboxFragment extends AbstractLocalFragment {
    public final Boolean value;

    public final String name;

    public final boolean mandatory;

    public CheckboxFragment(String name, Boolean value, boolean mandatory) {
        this.name = name;
        this.value = value;
        this.mandatory = mandatory;
    }

    public static class Mapper implements FragmentTypeMapperInterface {
        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            FormField formFieldAnnotation = FormFieldFragment.Mapper.getAnnotation(fragmentDto.field());

            return new CheckboxFragment(
                    fragmentDto.field().getName(),
                    this.getValue(fragmentDto),
                    formFieldAnnotation.isMandatory()
            );
        }

        public Boolean getValue(FieldDtoInterface fragmentDto) {
            Assert.isInstanceOf(Boolean.class, FieldUtility.getObject(fragmentDto), "Checkbox fragment value must be boolean.");

            return (Boolean) FieldUtility.getObject(fragmentDto);
        }
    }
}
