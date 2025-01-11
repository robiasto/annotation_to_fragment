package de.robiasto.app.infrastructure.fragment.grid;

/**
 * The GridColStartTypes enum represents the different types of column start positions in a grid layout.
 * <p>
 * Each enum value has an associated CSS class that can be used to apply the corresponding column start position style.
 * <p>
 * Each enum value has a getter method to retrieve its associated CSS class.
 */
public enum GridColStartTypes {
    COL_1(1),
    COL_2(2),
    COL_3(3),
    COL_4(4),
    COL_5(5),
    COL_6(6);

    private final int colCount;

    GridColStartTypes(int colCount) {
        this.colCount = colCount;
    }

    public String getCssClass() {
        return "sm:col-start-" + this.colCount;
    }
}
