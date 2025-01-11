package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select;

import de.robiasto.app.infrastructure.fragment.form.ImageTextSelectValueDto;
import de.robiasto.app.infrastructure.fragment.form.SelectValueDto;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.util.Assert;

import java.util.List;

/**
 * The SelectValueMapper class is a utility class that maps a field DTO to a list of SelectedValueDtoInterface objects.
 * It is used to retrieve a list of selected value objects from a field DTO.
 * The class provides a single public method, getValue, which takes a FieldDtoInterface object as a parameter and returns a list of SelectedValueDtoInterface objects.
 * <p>
 * Method:
 * - getValue: Retrieves a list of SelectedValueDtoInterface objects from a given FieldDtoInterface object.
 **/
public class ImageTextSelectValueMapper implements ImageTextSelectValueMapperInterface {
    @Override
    public List<ImageTextOptionFragment> getOptionFragments(FieldDtoInterface fieldDto, String name, boolean multiple) {
        Object fragmentDtoObject = FieldUtility.getObject(fieldDto);
        Assert.isInstanceOf(List.class, fragmentDtoObject, "Return value must be a List");
        return ((List<?>) fragmentDtoObject).stream().map(
                fragment -> {
                          if(fragment instanceof ImageTextSelectValueDto imageTextSelectValueDto) {
                              return new ImageTextOptionFragment(imageTextSelectValueDto, name, multiple);
                          }
                    throw new IllegalArgumentException("Return value must be a ImageTextSelectValueDto");
                }
        ).toList();
    }

    @Override
    public List<SelectValueDto> getSelectedValue(FieldDtoInterface fieldDto) {
        Object fragmentDtoObject = FieldUtility.getObject(fieldDto);
        Assert.isInstanceOf(List.class, fragmentDtoObject, "Return value must be a List");
       return ((List<?>) fragmentDtoObject).stream().map(
                fragment -> {
                    if(fragment instanceof ImageTextSelectValueDto imageTextSelectValueDto) {
                        return imageTextSelectValueDto.toSelectValueDto();
                    }

                    throw new IllegalArgumentException("Return value must be a FormValueDtoInterface");

                }
        ).toList();
    }
}
