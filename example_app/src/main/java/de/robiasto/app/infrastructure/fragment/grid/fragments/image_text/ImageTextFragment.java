package de.robiasto.app.infrastructure.fragment.grid.fragments.image_text;

import de.robiasto.app.infrastructure.fragment.grid.fragments.image.ImageFragment;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ImageTextFragment extends AbstractLocalFragment {
    public final String title;
    public final String value;
    public final List<ImageTextValueDto.LabelTextElement> textList;
    public final ImageFragment image;
    public final boolean isListElement;

    public ImageTextFragment(
            String title,
            String value,
            List<ImageTextValueDto.LabelTextElement> textList,
            ImageFragment image
    ) {
        this(title, value, textList, image, false);
    }

    @RequiredArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        private final ImageTextValueMapper imageValueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return this.imageValueMapper.getFragment(fragmentDto);
        }
    }
}
