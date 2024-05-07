package dev.mmieckowski;

import static dev.mmieckowski.Player.PLAYER_O;
import static dev.mmieckowski.Player.PLAYER_X;

public class PlayerTurnTracker {
    private Player currPlayer = PLAYER_X;

    Player getCurrentPlayer() {
        return currPlayer;
    }

    Player nextPLayer() {
        if (currPlayer == PLAYER_O) {
            currPlayer = PLAYER_X;
        } else {
            currPlayer = PLAYER_O;
        }
        return currPlayer;
    }

    void reset() {
        currPlayer = PLAYER_X;
    }
}
