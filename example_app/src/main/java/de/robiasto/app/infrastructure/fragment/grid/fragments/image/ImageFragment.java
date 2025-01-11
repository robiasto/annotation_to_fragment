package de.robiasto.app.infrastructure.fragment.grid.fragments.image;

import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ImageFragment extends AbstractLocalFragment {

    public final String src;

    public final String alt;

    @AllArgsConstructor
    public static class Mapper implements FragmentTypeMapperInterface {

        final StringMapper valueMapper;

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            return new ImageFragment(this.valueMapper.getValue(fragmentDto), "alt");
        }
    }
}
