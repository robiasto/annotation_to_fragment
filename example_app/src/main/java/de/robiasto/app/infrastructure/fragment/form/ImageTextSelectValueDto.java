package de.robiasto.app.infrastructure.fragment.form;

import de.robiasto.app.infrastructure.fragment.grid.fragments.image.ImageFragment;
import de.robiasto.app.infrastructure.fragment.grid.fragments.image_text.ImageTextValueDto;

import java.util.Comparator;
import java.util.List;

public class ImageTextSelectValueDto {
    public final String title;
    public final String value;
    public final String detailLink;
    public final List<ImageTextValueDto.LabelTextElement> textList;
    public final ImageFragment image;
    public final boolean selected;
    public final boolean isAdmin;

    public ImageTextSelectValueDto(
            String title,
            String value,
            String detailLink,
            String imageUrl,
            List<ImageTextValueDto.LabelTextElement> textList,
            boolean selected,
            boolean isAdmin
    ) {
        this.title = title;
        this.value = value;
        this.detailLink = detailLink;
        this.selected = selected;
        textList.sort(Comparator.comparingInt(ImageTextValueDto.LabelTextElement::order));
        this.textList = textList;
        this.image = new ImageFragment(imageUrl, title);
        this.isAdmin = isAdmin;
    }

    public SelectValueDto toSelectValueDto() {
        return new SelectValueDto(title, value, selected, false);
    }
}
