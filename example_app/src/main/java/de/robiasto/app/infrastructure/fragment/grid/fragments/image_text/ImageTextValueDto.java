package de.robiasto.app.infrastructure.fragment.grid.fragments.image_text;

import de.robiasto.app.infrastructure.fragment.grid.fragments.image.ImageFragment;

import java.util.Comparator;
import java.util.List;

public class ImageTextValueDto {
    public final String title;
    public final String value;
    public final List<LabelTextElement> textList;
    public final ImageFragment image;
    public final boolean isListElement;

    public ImageTextValueDto(
            String title,
            String value,
            String imageUrl,
            List<LabelTextElement> textList,
            boolean isListElement
    ) {
        this.title = title;
        this.value = value;
        textList.sort(Comparator.comparingInt(LabelTextElement::order));
        this.textList = textList;
        this.image = new ImageFragment(imageUrl, title);
        this.isListElement = isListElement;
    }

    public record LabelTextElement(String label, String text, int order) {
    }
}
