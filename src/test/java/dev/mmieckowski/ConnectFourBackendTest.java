package dev.mmieckowski;

import static dev.mmieckowski.BoardConfigurations.EMPTY;
import static dev.mmieckowski.BoardConfigurations.FULL;
import static dev.mmieckowski.BoardConfigurations.NO_SEQUENCE;
import static dev.mmieckowski.BoardConfigurations.ONE_X_TO_WIN_DIAGONALLY_PRIMARY;
import static dev.mmieckowski.BoardConfigurations.ONE_X_TO_WIN_DIAGONALLY_SECONDARY;
import static dev.mmieckowski.BoardConfigurations.ONE_X_TO_WIN_HORIZONTALLY;
import static dev.mmieckowski.BoardConfigurations.ONE_X_TO_WIN_VERTICALLY;
import static dev.mmieckowski.BoardConfigurations.copyArr2D;
import static dev.mmieckowski.ConnectFourBackend.ROWS;
import static dev.mmieckowski.Player.PLAYER_O;
import static dev.mmieckowski.Player.PLAYER_X;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ConnectFourBackendTest {
    @Mock
    private PlayerTurnTracker playerTurnTracker;
    @Mock
    private RowProvider rowProvider;
    @Mock
    private Referee referee;
    @InjectMocks
    ConnectFourBackend backend;
    private char[][] board;

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("End of Game Horizontally- Test")
    void whenThereIsASequenceOfFourThereIsEndOfGameHorizontallyTest(boolean ticTacToeMode) {
        board = copyArr2D(ONE_X_TO_WIN_HORIZONTALLY);
        backend.setBoard(board);
        backend.setTicTacToeMode(ticTacToeMode);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        lenient().when(rowProvider.nextRow(0)).thenReturn(ROWS - 1);
        when(referee.isGameOver(anyInt(), anyInt())).thenReturn(true);
        when(referee.getVictoryIndexes()).thenReturn(new ArrayList<>(List.of(new int[]{1, 2})));

        PlaceTokenResult result;
        if (!ticTacToeMode) {
            result = backend.placeToken(0, 0);
        } else {
            result = backend.placeToken(ROWS - 1, 0);
        }

        assertTrue(backend.isGameOver());
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertTrue(result.isGameOver());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("End of Game Vertically - Test")
    void whenThereIsASequenceOfFourThereIsEndOfGameVerticallyTest(boolean ticTacToeMode) {
        board = copyArr2D(ONE_X_TO_WIN_VERTICALLY);
        backend.setBoard(board);
        backend.setTicTacToeMode(ticTacToeMode);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        lenient().when(rowProvider.nextRow(0)).thenReturn(2);
        when(referee.isGameOver(anyInt(), anyInt())).thenReturn(true);
        when(referee.getVictoryIndexes()).thenReturn(new ArrayList<>(List.of(new int[]{1, 2})));

        PlaceTokenResult result;
        if (!ticTacToeMode) {
            result = backend.placeToken(0, 0);
        } else {
            result = backend.placeToken(2, 0);
        }

        assertTrue(backend.isGameOver());
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertTrue(result.isGameOver());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("End of Game diagonally primary - Test")
    void whenThereIsASequenceOfFourThereIsEndOfGameDiagonallyPrimaryTest(boolean ticTacToeMode) {
        board = copyArr2D(ONE_X_TO_WIN_DIAGONALLY_PRIMARY);
        backend.setBoard(board);
        backend.setTicTacToeMode(ticTacToeMode);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        lenient().when(rowProvider.nextRow(4)).thenReturn(ROWS - 1);
        lenient().when(referee.isGameOver(ROWS - 1, 4)).thenReturn(true);
        when(referee.getVictoryIndexes()).thenReturn(new ArrayList<>(List.of(new int[]{1, 2})));

        PlaceTokenResult result;
        if (!ticTacToeMode) {
            result = backend.placeToken(0, 4);
        } else {
            result = backend.placeToken(ROWS - 1, 4);
        }

        assertTrue(backend.isGameOver());
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertTrue(result.isGameOver());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("End of Game diagonally secondary - Test")
    void whenThereIsASequenceOfFourThereIsEndOfGameDiagonallySecondaryTest(boolean ticTacToeMode) {
        board = copyArr2D(ONE_X_TO_WIN_DIAGONALLY_SECONDARY);
        backend.setBoard(board);
        backend.setTicTacToeMode(ticTacToeMode);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        lenient().when(rowProvider.nextRow(2)).thenReturn(ROWS - 1);
        when(referee.isGameOver(ROWS - 1, 2)).thenReturn(true);
        when(referee.getVictoryIndexes()).thenReturn(new ArrayList<>(List.of(new int[]{1, 2})));

        PlaceTokenResult result = backend.placeToken(5, 2);

        assertTrue(backend.isGameOver());
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertTrue(result.isGameOver());

    }

    @Test
    @DisplayName("Placing token on taken field when ticTacToeMode  - Test")
    void placingTokenOnTakenFieldTest() {
        board = copyArr2D(FULL);
        backend.setBoard(board);
        backend.setTicTacToeMode(true);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_O);

        PlaceTokenResult result = backend.placeToken(0, 0);

        assertEquals('X', board[0][0]);
        assertFalse(result.isSuccess());
        assertNull(result.getPlayer());
        assertFalse(result.isGameOver());
    }

    @ParameterizedTest
    @ValueSource(booleans = {false, true})
    @DisplayName("Placing token on taken column - Test")
    void placingTokenOnTakenColumnTest(boolean ticTacToeMode) {
        board = copyArr2D(FULL);
        backend.setBoard(board);
        backend.setTicTacToeMode(ticTacToeMode);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_O);
        lenient().when(rowProvider.nextRow(0)).thenReturn(-1);

        PlaceTokenResult result = backend.placeToken(0, 0);

        assertEquals('X', board[0][0]);
        assertFalse(result.isSuccess());
        assertNull(result.getPlayer());
        assertFalse(result.isGameOver());

    }

    @Test
    @DisplayName("Drop token to the bottom while placing the token not on other Token or the bottom - Test")
    void placingTokenNotOnTheBottomWillDropTheTokenToTheBottomTest() {
        board = copyArr2D(EMPTY);
        backend.setBoard(board);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        when(rowProvider.nextRow(0)).thenReturn(ROWS - 1);
        when(referee.isGameOver(ROWS - 1, 0)).thenReturn(true);

        PlaceTokenResult result = backend.placeToken(0, 0);

        assertEquals('X', board[ROWS - 1][0]);
        assertEquals(' ', board[0][0]);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertFalse(result.isGameOver());
    }

    @Test
    @DisplayName("place Token in ticTacToeMode on empty field- Test")
    void placingTokenInTicTacToeModeWillPlaceItExactlyWhereRequestedIfEmptyFieldTest() {
        board = copyArr2D(EMPTY);
        backend.setBoard(board);
        backend.setTicTacToeMode(true);
        when(playerTurnTracker.getCurrentPlayer()).thenReturn(PLAYER_X);
        when(referee.isGameOver(0, 0)).thenReturn(false);

        PlaceTokenResult result = backend.placeToken(0, 0);

        assertEquals('X', board[0][0]);
        assertTrue(result.isSuccess());
        assertNotNull(result.getPlayer());
        assertEquals('X', result.getPlayer().getSymbol());
        assertFalse(result.isGameOver());
    }

    @Test
    void restartGameTest() {
        board = copyArr2D(NO_SEQUENCE);
        backend.setBoard(board);

        backend.restartGame();

        verify(rowProvider).reset();
        verify(referee).reset();
        verify(playerTurnTracker).reset();
    }

    @Test
    void restartBoardTest() {
        board = copyArr2D(FULL);
        backend.setBoard(board);

        backend.restartBoard();

        int i = 0;
        for (char[] b : board) {
            assertArrayEquals(EMPTY[i++], b);
        }
    }

}