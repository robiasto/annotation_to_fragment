package de.robiasto.app.infrastructure.templates.fragments.composition.table;

import de.robiasto.app.infrastructure.fragment.grid.FragmentTypes;
import de.robiasto.app.infrastructure.fragment.grid.GridColFragmentConfiguration;
import de.robiasto.fragment_annotation_core.annotation.post_init.add_css_class.AddCssClassOnType;
import de.robiasto.fragment_annotation_core.interfaces.AnnotationViewInterface;

import java.util.List;

public class TableTestModel implements AnnotationViewInterface {
    @GridColFragmentConfiguration(
            fragmentType = FragmentTypes.TABLE,
            sorting = 1
    )
    @AddCssClassOnType("table")
    private final List<RowTestModel> rows;

    public TableTestModel(List<RowTestModel> rows) {
        this.rows = rows;
    }
}
