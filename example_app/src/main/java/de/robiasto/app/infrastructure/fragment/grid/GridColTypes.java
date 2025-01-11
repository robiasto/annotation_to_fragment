package de.robiasto.app.infrastructure.fragment.grid;

/**
 * The {@code GridColTypes} enum represents the different types of grid column configurations that can be used in an application.
 * <p>
 * The enum has the following values:
 * - COL_1: Represents a grid column with a width of 1
 * - COL_2: Represents a grid column with a width of 2
 * - COL_3: Represents a grid column with a width of 3
 * - COL_4: Represents a grid column with a width of 4
 * - COL_5: Represents a grid column with a width of 5
 * - COL_6: Represents a grid column with a width of 6
 * <p>
 * Each enum value has a corresponding CSS class that can be used to style the grid column.
 * <p>
 * The GridColTypes enum provides a {@code getCssClass()} method to retrieve the CSS class associated with a specific grid column type.
 */
public enum GridColTypes {
    COL_1(1),
    COL_2(2),
    COL_3(3),
    COL_4(4),
    COL_5(5),
    COL_6(6);

    private final int colCount;

    GridColTypes(int colCount) {
        this.colCount = colCount;
    }

    public String getCssClass() {
        return "sm:col-span-" + this.colCount;
    }
}
