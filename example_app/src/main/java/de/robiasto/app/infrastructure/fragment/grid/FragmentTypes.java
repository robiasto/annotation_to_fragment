package de.robiasto.app.infrastructure.fragment.grid;

import de.robiasto.app.infrastructure.fragment.grid.fragments.form.FormFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.fragment.Fragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.headline.HeadlineFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image.ImageFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextFragmentList;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueMapper;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.LinkFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.table.FragmentTable;
import de.robiasto.app.infrastructure.fragment.grid.fragments.text.TextFragment;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationCompositionMapper;
import de.robiasto.fragment_annotation_core.integration.mapper.CompositionMapper;
import de.robiasto.fragment_annotation_core.integration.mapper.StringMapper;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeEnumInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import lombok.Getter;


@Getter
public enum FragmentTypes implements FragmentTypeEnumInterface {
    FORM(new FormFragment.Mapper(new CompositionMapper())),
    FRAGMENT(new Fragment.Mapper()),
    HEADLINE(new HeadlineFragment.Mapper(new StringMapper())),
    LINK(new LinkFragment.Mapper(new StringMapper())),
    TABLE(new FragmentTable.Mapper(new PaginationCompositionMapper())),
    TEXT_FREE(new TextFragment.Mapper(new StringMapper(), TextFragment.Template.TEXT_FREE_FRAGMENT)),
    TEXT_PARAGRAPH(new TextFragment.Mapper(new StringMapper(), TextFragment.Template.TEXT_PARAGRAPH_FRAGMENT)),
    IMAGE(new ImageFragment.Mapper(new StringMapper())),
    IMAGE_TEXT(new ImageTextFragment.Mapper(new ImageTextValueMapper())),
    IMAGE_TEXT_LIST(new ImageTextFragmentList.Mapper(new ImageTextValueMapper()));

    private final FragmentTypeMapperInterface fragmentMapper;

    FragmentTypes(FragmentTypeMapperInterface mapperClass) {
        this.fragmentMapper = mapperClass;
    }
}
