package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.select;

import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * The SelectValueMapper class is a utility class that maps a field DTO to a list of SelectedValueDtoInterface objects.
 * It is used to retrieve a list of selected value objects from a field DTO.
 * The class provides a single public method, getValue, which takes a FieldDtoInterface object as a parameter and returns a list of SelectedValueDtoInterface objects.
 * <p>
 * Method:
 * - getValue: Retrieves a list of SelectedValueDtoInterface objects from a given FieldDtoInterface object.
 **/
public class SelectValueMapper implements SelectValueMapperInterface {

    public List<SelectValueDto> getValue(FieldDtoInterface fieldDto) {
        Object fragmentDtoObject = FieldUtility.getObject(fieldDto);
        Assert.isInstanceOf(List.class, fragmentDtoObject, "Return value must be a List");

        return new ArrayList<>(this.getSelectValueDtos((List<?>) fragmentDtoObject));
    }

    private List<SelectValueDto> getSelectValueDtos(List<?> fragmentDtoObject) {
        return fragmentDtoObject.stream().map(
                fragment -> {
                    Assert.isInstanceOf(
                            SelectValueDto.class,
                            fragment,
                            "Return value must be a FormValueDtoInterface"
                    );

                    if (fragment instanceof SelectValueDto selectValueDto) {
                        return selectValueDto;
                    }

                    throw new IllegalArgumentException("Return value must be a FormValueDtoInterface");
                }
        ).toList();
    }
}
