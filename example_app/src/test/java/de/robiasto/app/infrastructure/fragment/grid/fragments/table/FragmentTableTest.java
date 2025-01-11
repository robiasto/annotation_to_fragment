package de.robiasto.app.infrastructure.fragment.grid.fragments.table;

import de.robiasto.fragment_annotation_core.interfaces.fragment.FragmentInterface;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FragmentTableTest {

    @Test
    void testGetHeadCols_ReturnsHeadColsWhenInitialized() {
        // Arrange
        FragmentColHead[] headCols = {mock(FragmentColHead.class), mock(FragmentColHead.class)};
        FragmentColBody[][] rows = { {mock(FragmentColBody.class)} };
        FragmentInterface pagination = mock(FragmentInterface.class);

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentColHead[] result = fragmentTable.getHeadCols();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.length);
        assertArrayEquals(headCols, result);
    }

    @Test
    void testGetHeadCols_ReturnsNullWhenRowsIsEmpty() {
        // Arrange
        FragmentColBody[][] rows = {}; // No rows
        FragmentColHead[] headCols = {mock(FragmentColHead.class)};
        FragmentInterface pagination = mock(FragmentInterface.class);

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentColHead[] result = fragmentTable.getHeadCols();

        // Assert
        assertNull(result);
    }

    @Test
    void testGetHeadCols_ReturnsNullWhenHeadColsIsNull() {
        // Arrange
        FragmentColBody[][] rows = { {mock(FragmentColBody.class)} };
        FragmentColHead[] headCols = null; // No head columns
        FragmentInterface pagination = mock(FragmentInterface.class);

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentColHead[] result = fragmentTable.getHeadCols();

        // Assert
        assertNull(result);
    }

    @Test
    void testGetRows_ReturnsRowsWhenInitialized() {
        // Arrange
        FragmentColHead[] headCols = {mock(FragmentColHead.class)};
        FragmentColBody[][] rows = {
                {mock(FragmentColBody.class), mock(FragmentColBody.class)},
                {mock(FragmentColBody.class)}
        };
        FragmentInterface pagination = mock(FragmentInterface.class);

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentColBody[][] result = fragmentTable.getRows();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.length);
        assertArrayEquals(rows, result);
    }

    @Test
    void testGetPagination_ReturnsPaginationWhenInitialized() {
        // Arrange
        FragmentColHead[] headCols = {mock(FragmentColHead.class)};
        FragmentColBody[][] rows = { {mock(FragmentColBody.class)} };
        FragmentInterface pagination = mock(FragmentInterface.class);

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentInterface result = fragmentTable.getPagination();

        // Assert
        assertNotNull(result);
        assertEquals(pagination, result);
    }

    @Test
    void testGetPagination_ReturnsNullWhenNotInitialized() {
        // Arrange
        FragmentColHead[] headCols = {mock(FragmentColHead.class)};
        FragmentColBody[][] rows = {{mock(FragmentColBody.class)}};
        FragmentInterface pagination = null; // No pagination

        FragmentTable fragmentTable = new FragmentTable(rows, headCols, pagination);

        // Act
        FragmentInterface result = fragmentTable.getPagination();

        // Assert
        assertNull(result);
    }
}
