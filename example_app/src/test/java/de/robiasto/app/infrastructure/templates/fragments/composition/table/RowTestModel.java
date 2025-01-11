package de.robiasto.app.infrastructure.templates.fragments.composition.table;

import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.LinkConfiguration;
import de.robiasto.app.infrastructure.fragment.grid.LinkType;
import de.robiasto.app.infrastructure.fragment.grid.TableCol;
import de.robiasto.app.infrastructure.fragment.grid.fragments.link.LinkFragment;
import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.AddCssClassOnType;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;
import jakarta.validation.constraints.NotBlank;

public class RowTestModel implements AnnotationViewInterface {
    @TableCol(
            fragmentType = FragmentTypes.TEXT_PARAGRAPH,
            sorting = 1,
            label = "rowTestModel.name"
    )
    @AddCssClassOnType("text-paragraph")
    private final String name;

    @TableCol(
            fragmentType = FragmentTypes.LINK,
            sorting = 2,
            label = "rowTestModel.link"
    )
    @LinkConfiguration(translation = "link")
    @AddCssClassOnType("link")
    @NotBlank
    private final String link;

    @TableCol(
            fragmentType = FragmentTypes.FRAGMENT,
            sorting = 3,
            label = "rowTestModel.linkFragment"
    )
    @AddCssClassOnType("link-fragment")
    private final LinkFragment linkFragment;

    public RowTestModel(int i) {
        this.name = "rowTestModel.name" + i;
        this.link = "url" + i;
        this.linkFragment = new LinkFragment("link fragment" + i, "", LinkType.LINK, "url");
    }
}
