package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;

import java.util.List;

/**
 * The SelectValueMapper class is a utility class that maps a field DTO to a list of SelectedValueDtoInterface objects.
 * It is used to retrieve a list of selected value objects from a field DTO.
 * The class provides a single public method, getValue, which takes a FieldDtoInterface object as a parameter and returns a list of SelectedValueDtoInterface objects.
 * <p>
 * Method:
 * - getValue: Retrieves a list of SelectedValueDtoInterface objects from a given FieldDtoInterface object.
 **/
public interface SelectValueMapperInterface {
    List<SelectValueDto> getValue(FieldDtoInterface fieldDto);
}
