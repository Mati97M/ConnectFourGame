package dev.mmieckowski;

import java.util.List;
import java.util.Optional;

public class PlaceTokenResult {
    private boolean success;
    private Optional<String> errorMessage;
    private Player player;
    private int actualRow;
    private int actualCol;
    private final List<int[]> victoryCells;

    //winner - game over
    public PlaceTokenResult(Player winner, List<int[]> victoryCells, int actualRow, int actualCol) {
        initializeSuccess(winner, actualRow, actualCol);
        this.victoryCells = List.copyOf(victoryCells);
    }

    //token can be placed, but there is no winner yet
    public PlaceTokenResult(Player player, int actualRow, int actualCol) {
        initializeSuccess(player, actualRow, actualCol);
        this.victoryCells = null;
    }


    //token could not be placed on the board
    public PlaceTokenResult(String errorMsg) {
        this.success = false;
        this.errorMessage = Optional.of(errorMsg);
        this.victoryCells = null;
        this.player = null;
    }

    public Optional<String> getErrorMessage() {
        return errorMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public boolean isGameOver() {
        return victoryCells != null && !victoryCells.isEmpty();
    }

    public Player getPlayer() {
        return player;
    }

    public List<int[]> getVictoryCells() {
        return victoryCells;
    }

    public int getActualRow() {
        return actualRow;
    }

    public int getActualCol() {
        return actualCol;
    }

    private void initializeSuccess(Player player, int actualRow, int actualCol) {
        this.success = true;
        this.errorMessage = Optional.empty();
        this.player = player;
        this.actualRow = actualRow;
        this.actualCol = actualCol;
    }

}
