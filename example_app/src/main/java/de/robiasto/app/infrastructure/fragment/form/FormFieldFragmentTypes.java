package de.robiasto.app.infrastructure.fragment.form;

import de.robiasto.app.infrastructure.fragment.form.fragment.type.checkbox.CheckboxFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.file.FileFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.input.InputFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select.ImageTextSelectFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select.ImageTextSelectValueMapper;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.radio_group.RadioGroupFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select.SelectFragment;
import de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select.SelectValueMapper;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import lombok.Getter;

@Getter
public enum FormFieldFragmentTypes implements FragmentTypeEnumInterface {
    INPUT_TEXT(new InputFragment.Mapper(new StringMapper(), "text"), true),
    FILE(new FileFragment.Mapper(), true),
    CHECKBOX(new CheckboxFragment.Mapper(),true),
    SELECT(new SelectFragment.Mapper(false,new SelectValueMapper()), true),
    SELECT_IMAGE_TEXT(new ImageTextSelectFragment.Mapper(false, new ImageTextSelectValueMapper()), true),
    SELECT_MULTIPLE_IMAGE_TEXT(new ImageTextSelectFragment.Mapper(true, new ImageTextSelectValueMapper()), true),
    SELECT_MULTIPLE(new SelectFragment.Mapper(true, new SelectValueMapper()), true),
    RADIOBUTTONS(new RadioGroupFragment.Mapper(new SelectValueMapper()), true),
    HIDDEN(new InputFragment.Mapper(new StringMapper(), "hidden"), false),
    PASSWORD(new InputFragment.Mapper(new StringMapper(), "password"), true);

    private final FragmentTypeMapperInterface fragmentMapper;
    private final boolean displayLabel;


    FormFieldFragmentTypes(FragmentTypeMapperInterface mapperClass, boolean displayLabel) {
        this.fragmentMapper = mapperClass;
        this.displayLabel = displayLabel;
    }
}
