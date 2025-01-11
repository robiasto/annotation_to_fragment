package de.robiasto.app.infrastructure.fragment.grid.fragments.image_text;

import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ImageTextFragmentList extends AbstractLocalFragment {
    public final List<ImageTextFragment> imageTextValues;

    @RequiredArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {
        private final ImageTextValueMapper imageValueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return new ImageTextFragmentList(
                    this.imageValueMapper.getFragments(fragmentDto)
            );
        }
    }
}
