package de.robiasto.app.infrastructure.fragment.form.fragment.type.select.image_text_select;

import de.robiasto.app.infrastructure.fragment.form.ImageTextSelectValueDto;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextFragment;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractLocalFragment;

public class ImageTextOptionFragment extends AbstractLocalFragment {
    public final ImageTextFragment imageTextFragment;
    public final String name;
    public final String value;
    public final String detailLink;
    public final boolean multiple;
    public final boolean selected;
    public final boolean isAdmin;


    public ImageTextOptionFragment(
            ImageTextSelectValueDto valueDto,String name, boolean multiple
    ) {
        this.imageTextFragment = new ImageTextFragment(
                valueDto.title,
                valueDto.value,
                valueDto.textList,
                valueDto.image,
                true
        );

        this.name = name;
        this.value = valueDto.value;
        this.detailLink = valueDto.detailLink;
        this.multiple = multiple;
        this.selected = valueDto.selected;
        this.isAdmin = valueDto.isAdmin;
    }
}
