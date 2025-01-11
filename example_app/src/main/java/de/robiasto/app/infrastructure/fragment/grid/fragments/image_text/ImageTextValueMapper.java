package de.robiasto.app.infrastructure.fragment.grid.fragments.image_text;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.utilities.FieldUtility;
import org.springframework.util.Assert;

import java.util.List;

public class ImageTextValueMapper {
    public List<ImageTextFragment> getFragments(FieldDtoInterface fieldDto) {
        Object fragmentDtoObject = FieldUtility.getObject(fieldDto);
        Assert.isInstanceOf(List.class, fragmentDtoObject, "Return value must be a List");
        return ((List<?>) fragmentDtoObject).stream().map(
                fragment -> {
                          if(fragment instanceof ImageTextValueDto imageTextValueDto) {
                              return this.getFragmentFromValueDto(imageTextValueDto, true);
                          }
                    throw new IllegalArgumentException("Return value must be a ImageTextSelectValueDto");
                }
        ).toList();
    }

    public ImageTextFragment getFragment(FieldDtoInterface fieldDto) {
        Object fragment = FieldUtility.getObject(fieldDto);
        Assert.isInstanceOf(ImageTextValueDto.class, fragment, "Return value must be a ImageTextValueDto");
        ImageTextValueDto imageTextValueDto = (ImageTextValueDto) fragment;

        return this.getFragmentFromValueDto(imageTextValueDto, false);
    }

    private ImageTextFragment getFragmentFromValueDto(ImageTextValueDto imageTextValueDto, boolean listElement) {

        return new ImageTextFragment(
                imageTextValueDto.title,
                imageTextValueDto.value,
                imageTextValueDto.textList,
                imageTextValueDto.image,
                listElement
        );
    }
}
