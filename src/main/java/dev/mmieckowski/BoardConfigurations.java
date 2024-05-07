package dev.mmieckowski;

import static dev.mmieckowski.ConnectFourBackend.COLS;
import static dev.mmieckowski.ConnectFourBackend.ROWS;
import java.util.Arrays;

public class BoardConfigurations {
    public static char[][] copyArr2D(char[][] target) {
        char[][] copy = new char[ROWS][COLS];
        int i = 0;
        for (char[] arr : target) {
            copy[i++] = Arrays.copyOf(arr, COLS);
        }
        return copy;
    }

    static final char[][] ONE_X_TO_WIN_HORIZONTALLY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'X', 'X', 'X', ' ', ' ', ' '}
    };
    static final char[][] ONE_X_TO_WIN_VERTICALLY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', ' ', ' ', ' ', ' ', ' ', ' '}
    };
    static final char[][] ONE_X_TO_WIN_DIAGONALLY_PRIMARY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'X', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'X', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };
    static final char[][] ONE_X_TO_WIN_DIAGONALLY_SECONDARY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', 'X', ' '},
            {' ', ' ', ' ', ' ', 'X', ' ', ' '},
            {' ', ' ', ' ', 'X', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    static final char[][] EMPTY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };
    static final char[][] FULL = new char[][]{
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'},
            {'X', 'X', 'X', 'X', 'X', 'X', 'X'}
    };

    static final char[][] FOUR_HORIZONTALLY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', 'X', 'X', 'X', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };
    static final char[][] FOUR_VERTICALLY = new char[][]{
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };

    static final char[][] FOUR_DIAGONALLY_PRIMARY = new char[][]{
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', 'X', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', 'X', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}

    };
    static final char[][] FOUR_DIAGONALLY_SECONDARY = new char[][]{
            {' ', ' ', ' ', ' ', 'X', ' ', ' '},
            {' ', ' ', ' ', 'X', ' ', ' ', ' '},
            {' ', ' ', 'X', ' ', ' ', ' ', ' '},
            {' ', 'X', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}

    };

    static final char[][] NO_SEQUENCE = new char[][]{
            {'X', 'X', 'X', ' ', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '},
            {'X', 'X', ' ', 'X', 'X', ' ', ' '},
            {'X', ' ', 'X', ' ', ' ', ' ', ' '},
            {'X', 'X', ' ', 'X', 'X', ' ', ' '},
            {' ', ' ', ' ', ' ', ' ', ' ', ' '}
    };
}
