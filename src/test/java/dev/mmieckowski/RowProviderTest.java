package dev.mmieckowski;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("RowProvider")
class RowProviderTest {
    RowProvider rowProvider;

    @BeforeEach
    void setUp() {
        rowProvider = new RowProvider();
    }

    @Test
    @DisplayName("Populating the column - Test")
    void nextRowCallIncreasesNumberOfFilledRowsInColumnUntilItIsFullTest() {
        int expectedRowNumber = rowProvider.getNumOfAllRows() - 1;
        for (int i = 0; i < rowProvider.getNumOfAllRows(); i++) {
            assertEquals(expectedRowNumber--, rowProvider.nextRow(0));
        }
        assertEquals(-1, rowProvider.nextRow(0));
    }

    @Test
    @DisplayName("After reset the bottom of the column is empty - Test")
    void reset() {
        for (int i = 0; i < rowProvider.getNumOfAllRows(); i++) {
            rowProvider.nextRow(0);
        }
        assertEquals(-1, rowProvider.nextRow(0));
        rowProvider.reset();

        assertEquals(rowProvider.getNumOfAllRows() - 1, rowProvider.nextRow(0));
    }
}