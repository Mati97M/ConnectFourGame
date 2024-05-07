package dev.mmieckowski;

import java.util.Arrays;

public class ConnectFourBackend {
    public static final int ROWS = 6;
    public static final int COLS = 7;
    public static final int SIZE = 50;
    private boolean gameOver;
    private char[][] board;
    private boolean ticTacToeMode;
    private final PlayerTurnTracker turnTracker;
    private final RowProvider rowProvider;
    private final Referee referee;

    ConnectFourBackend() {
        this.ticTacToeMode = false;
        this.turnTracker = new PlayerTurnTracker();
        this.rowProvider = new RowProvider();
        this.gameOver = false;
        this.board = new char[ROWS][COLS];
        this.referee = new Referee(this.board);
    }

    boolean isGameOver() {
        return gameOver;
    }

    ConnectFourBackend(PlayerTurnTracker turnTracker, RowProvider rowProvider, Referee referee) {
        this.gameOver = false;
        this.ticTacToeMode = false;
        this.turnTracker = turnTracker;
        this.rowProvider = rowProvider;
        this.referee = referee;
    }

    void setTicTacToeMode(boolean ticTacToeMode) {
        this.ticTacToeMode = ticTacToeMode;
    }

    void setBoard(char[][] board) {
        this.board = board;
    }

    Player nextPlayer() {
        return turnTracker.nextPLayer();
    }

    Player getCurrentPlayer() {
        return turnTracker.getCurrentPlayer();
    }

    PlaceTokenResult placeToken(int currRow, int currCol) throws InvalidPlayerSymbolException {
        char playerSymbol = turnTracker.getCurrentPlayer().getSymbol();
        if (isInvalidSymbol(playerSymbol)) {
            throw new InvalidPlayerSymbolException("Incorrect Player symbol");
        }

        if (!ticTacToeMode) {
            currRow = rowProvider.nextRow(currCol);
            if (currRow == -1) {
                String errorMsg = "Column not available! Try Another One";
                return new PlaceTokenResult(errorMsg);
            }
        } else if (isTaken(currRow, currCol)) {
            String errorMsg = "Field not available! Try Another One";
            return new PlaceTokenResult(errorMsg);
        }

        board[currRow][currCol] = playerSymbol;

        if (referee.isGameOver(currRow, currCol)) {
            gameOver = true;
            return new PlaceTokenResult(turnTracker.getCurrentPlayer(), referee.getVictoryIndexes(), currRow, currCol);
        }
        return new PlaceTokenResult(turnTracker.getCurrentPlayer(), currRow, currCol);

    }

    void restartGame() {
        restartBoard();
        gameOver = false;
        rowProvider.reset();
        referee.reset();
        turnTracker.reset();
        System.out.println("Game restarted");
    }

    void restartBoard() {
        for (char[] arr : board) {
            Arrays.fill(arr, ' ');
        }
    }

    private boolean isTaken(int currRow, int currCol) {
        return board[currRow][currCol] != ' ';
    }

    private static boolean isInvalidSymbol(char playerSymbol) {
        for (Player player : Player.values()) {
            if (playerSymbol == player.getSymbol()) {
                return false;
            }
        }
        return true;
    }
}
