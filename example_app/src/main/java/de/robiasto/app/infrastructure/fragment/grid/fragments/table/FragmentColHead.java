package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.app.infrastructure.fragment.grid.TableCol;
import de.robiasto.app.infrastructure.fragment.plain.pagination.PaginationCompositionMapper;
import de.robiasto.fragment_annotation_core.interfaces.field.FieldDtoInterface;
import de.robiasto.fragment_annotation_core.utilities.templates.AbstractFragment;
import lombok.Getter;
import org.springframework.data.domain.Sort;

import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * The `FragmentColHead` class is a subclass of `AbstractFragment` that represents a fragment for the column header of a table. It holds information about the label, name, sortable
 * status, sort parameters, active sorting, and sorting direction chosen for the column.
 * <p>
 * This class provides two constructors:
 * 1. `FragmentColHead(String name, String label, boolean sortable, String[] sortableKeys, boolean activeSorting, String sortingDirection)`: This constructor initializes the `Fragment
 * ColHead` object with the specified name, label, sortable status, sortable keys, active sorting status, and sorting direction. It also sets the default fragment path and fragment
 * function using the `AbstractFragment` superclass constructor. The sort parameters and sorting direction chosen are calculated based on the sortable keys and sorting direction.
 * 2. `FragmentColHead(String name, String title)`: This constructor is a convenience constructor that calls the first constructor with default values for sortable, sortable keys
 * , active sorting, and sorting direction, and sets the label to the specified title.
 * <p>
 * The class also provides getter methods for accessing the label, name, sortable status, sort parameters, active sorting status, and sorting direction chosen.
 * <p>
 * The `Mapper` nested class within `FragmentColHead` is a helper class that provides mapping functionality for `FragmentColHead` objects. It contains a reference to a `Pagination
 * CompositionMapper` object and defines several helper methods for retrieving the `FragmentColHead` objects based on a `FieldDtoInterface` parameter. These methods handle the different
 * types of `FieldDtoInterface` objects (page or list) and extract the necessary information from the field annotations to create the `FragmentColHead` objects.
 * <p>
 * Please refer to the code comments and the documentation of the referenced classes and interfaces for more information.
 */
@Getter
public class FragmentColHead extends AbstractFragment {
    private final String label;

    private final String name;

    private final boolean sortable;

    private final String sortParams;

    private final boolean activeSorting;

    private final String sortingDirectionChosen;

    private final boolean hideOnMobile;

    public FragmentColHead(
            String name,
            String label,
            boolean sortable,
            String[] sortableKeys,
            boolean activeSorting,
            String sortingDirection,
            boolean hideOnMobile
    ) {
        super("fragments/composition/table/colHead.html", "colHead");

        this.name = name;
        this.label = label;
        this.sortable = sortable;
        this.sortParams = String.join("&",
                                      Arrays.stream(sortableKeys)
                                            .map(sortableKey -> "sort=" + sortableKey + "," + sortingDirection)
                                            .toArray(String[]::new)
        ) + "&update=1";
        this.activeSorting = activeSorting;
        this.sortingDirectionChosen = sortingDirection.equals("ASC") ? "DESC" : "ASC";
        this.hideOnMobile = hideOnMobile;
    }

    public FragmentColHead(
            String name,
            String title
    ) {
        this(name, title, false, new String[]{}, false, "ASC", false);
    }

    static class Mapper {
        private final PaginationCompositionMapper compositionMapper;

        public Mapper(PaginationCompositionMapper compositionMapper) {
            this.compositionMapper = compositionMapper;
        }

        public FragmentColHead[] getFragments(FieldDtoInterface fragmentDto) {
            return this.compositionMapper.isPage(fragmentDto)
                    ? this.getPageFragments(fragmentDto)
                    : this.getListFragments(fragmentDto);
        }

        private FragmentColHead[] getPageFragments(FieldDtoInterface fragmentDto) {
            Sort activeSorting = this.compositionMapper.getPages(fragmentDto).getPageable().getSort();
            FieldDtoInterface[] fieldDtos = this.compositionMapper.getFields(fragmentDto);

            return Arrays.stream(fieldDtos)
                         .map(fieldDto -> {
                                  Field field = fieldDto.field();
                                  TableCol tableColConfiguration = field.getAnnotation(
                                          TableCol.class
                                  );

                                  String[] sortableKeysFromAnnotation =
                                          tableColConfiguration.sortableKeys().length > 0
                                                  ? tableColConfiguration.sortableKeys()
                                                  : new String[]{tableColConfiguration.sortableKey()};

                                  boolean activeOrder = this.isActiveSorting(activeSorting, sortableKeysFromAnnotation);

                                  return new FragmentColHead(
                                          field.getName(),
                                          tableColConfiguration.label(),
                                          tableColConfiguration.sortable(),
                                          sortableKeysFromAnnotation,
                                          activeOrder,
                                          this.getSortingDirection(activeSorting, activeOrder),
                                            tableColConfiguration.hideOnMobile()
                                  );
                              }
                         )
                         .toArray(FragmentColHead[]::new);
        }

        private FragmentColHead[] getListFragments(FieldDtoInterface fragmentDto) {
            FieldDtoInterface[] fieldDtos = this.compositionMapper.getFields(fragmentDto);

            return Arrays.stream(fieldDtos)
                         .map(fieldDto -> {
                                  Field field = fieldDto.field();

                                  return new FragmentColHead(
                                          field.getName(),
                                          field.getAnnotation(
                                                  TableCol.class
                                          ).label()
                                  );
                              }
                         )
                         .toArray(FragmentColHead[]::new);
        }

        private String getSortingDirection(Sort activeSorting, boolean activeOrder) {
            return activeSorting.stream()
                                .findFirst().filter(order -> activeOrder && order.getDirection().name().equals("ASC"))
                                .map(order -> "DESC")
                                .orElse("ASC");
        }

        private boolean isActiveSorting(Sort activeSorting, String[] sortableKeys) {
            return activeSorting.stream()
                                .anyMatch(order -> Arrays.stream(sortableKeys)
                                                         .toList()
                                                         .contains(order.getProperty()));
        }
    }
}
