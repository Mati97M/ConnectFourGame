package dev.mmieckowski;

public enum Player {
    PLAYER_O('O'),
    PLAYER_X('X');

    private char symbol;

    public char getSymbol() {
        return symbol;
    }

    Player(char symbol) {
        this.symbol = symbol;
    }


}
