package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;

import java.util.List;

public interface ImageTextSelectValueMapperInterface {
    List<ImageTextOptionFragment> getOptionFragments(FieldDtoInterface fieldDto, String name, boolean multiple);
    List<SelectValueDto> getSelectedValue(FieldDtoInterface fieldDto);
}
