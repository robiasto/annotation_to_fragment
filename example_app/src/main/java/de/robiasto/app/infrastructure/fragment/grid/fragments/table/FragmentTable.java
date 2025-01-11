package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.app.infrastructure.fragment.grid.fragments.text.TextFragment;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationCompositionMapper;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationFragment;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import de.robiasto.fragment_annotation_core.interfaces.fragment.type.FragmentTypeMapperInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The FragmentTable class extends the AbstractFragment class and represents a fragment that represents a table in a composition. It implements the FragmentInterface interface.
 * <p>
 * The FragmentTable class has the following properties:
 * - headCols: an array of FragmentColHead objects that represents the column headers of the table.
 * - rows: a two-dimensional array of FragmentColBody objects that represents the rows and cells of the table.
 * - pagination: a FragmentInterface object that represents the pagination component of the table.
 * <p>
 * The FragmentTable class has the following methods:
 * - Constructor: creates a new FragmentTable object with the specified rows, headCols, and pagination.
 * - getHeadCols(): returns the column headers of the table.
 * - getRows(): returns the rows and cells of the table.
 * - getPagination(): returns the pagination component of the table.
 * <p>
 * The FragmentTable class also contains a static nested class called Mapper, which implements the FragmentTypeMapperInterface interface. This class is used to map a Fragment Dto
 * Interface object to a FragmentInterface object.
 */
public class FragmentTable extends AbstractFragment {
    private final FragmentColHead[] headCols;

    private final FragmentColBody[][] rows;

    private final FragmentInterface pagination;

    public FragmentTable(
            FragmentColBody[][] rows,
            FragmentColHead[] headCols,
            FragmentInterface pagination
    ) {
        super("fragments/composition/table/table.html", "table");

        this.pagination = pagination;

        if (rows.length == 0) {
            this.headCols = null;
            this.rows = null;

            return;
        }

        this.headCols = headCols;
        this.rows = rows;
    }

    public FragmentColHead[] getHeadCols() {
        return headCols;
    }

    public FragmentColBody[][] getRows() {
        return rows;
    }

    public FragmentInterface getPagination() {
        return pagination;
    }

    public static class Mapper implements FragmentTypeMapperInterface {

        private final PaginationCompositionMapper compositionMapper;

        private final FragmentColHead.Mapper headRowColFragmentMapper;

        public Mapper(PaginationCompositionMapper compositionMapper) {
            this.compositionMapper = compositionMapper;
            this.headRowColFragmentMapper = new FragmentColHead.Mapper(this.compositionMapper);
        }

        @Override
        public FragmentInterface getAnnotationMapper(FieldDtoInterface fragmentDto) {
            if(this.compositionMapper.getValue(fragmentDto).length == 0){
                return new TextFragment("Keine Daten gefunden.", TextFragment.Template.TEXT_PARAGRAPH_FRAGMENT);
            }

            return new FragmentTable(
                    this.getColBody(fragmentDto),
                    this.headRowColFragmentMapper.getFragments(fragmentDto),
                    this.getPagination(fragmentDto)
            );
        }

        private FragmentInterface getPagination(FieldDtoInterface fragmentDto) {
            return this.compositionMapper.isPage(fragmentDto) ? (new PaginationFragment.Mapper()).getAnnotationMapper(
                    fragmentDto) : null;
        }

        private FragmentColBody[][] getColBody(FieldDtoInterface fragmentDto) {
            return Arrays.stream(this.compositionMapper.getValue(fragmentDto))
                         .map(
                                 row -> {
                                     List<FragmentColBody> rowCols = new ArrayList<>();
                                     Arrays.stream((Object[]) row).forEach(rowCol -> {
                                         if (!(rowCol instanceof FragmentColBody)) {
                                             throw new InvalidTableFragmentException(
                                                     "Table row cols must be of type TableRowColFragment[].\n"
                                                             + rowCol.getClass().getSimpleName()
                                                             + " is not a valid type."
                                             );
                                         }
                                         rowCols.add((FragmentColBody) rowCol);
                                     });

                                     return rowCols.toArray(new FragmentColBody[1]);
                                 }
                         ).toArray(FragmentColBody[][]::new);
        }
    }
}
