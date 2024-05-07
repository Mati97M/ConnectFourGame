package dev.mmieckowski;

import static dev.mmieckowski.ConnectFourBackend.COLS;
import static dev.mmieckowski.ConnectFourBackend.ROWS;
import java.util.Arrays;

public class RowProvider {
    private final int[] countOfFilledRows;
    private final int numOfAllRows;

    RowProvider() {
        this.numOfAllRows = ROWS;
        this.countOfFilledRows = new int[COLS];
    }

    int nextRow(int colNum) {
        if (countOfFilledRows[colNum] >= numOfAllRows) {
            return -1;
        }
        int availableRow = numOfAllRows - countOfFilledRows[colNum] - 1;
        countOfFilledRows[colNum] = countOfFilledRows[colNum] + 1;
        return availableRow;
    }

    void reset() {
        Arrays.fill(countOfFilledRows, 0);
    }

    int getNumOfAllRows() {
        return numOfAllRows;
    }
}