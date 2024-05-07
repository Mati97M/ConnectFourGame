package dev.mmieckowski;

import static dev.mmieckowski.Player.PLAYER_X;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("PlayerTurnTracker")
class PlayerTurnTrackerTest {
    PlayerTurnTracker playerTurnTracker;

    @BeforeEach
    void setUp() {
        playerTurnTracker = new PlayerTurnTracker();
    }

    @Test
    @DisplayName("Value from getCurrentPlayer is never null - Test")
    void getCurrentPlayerIsNotNullTest() {
        assertNotNull(playerTurnTracker.getCurrentPlayer());
    }

    @DisplayName("Next PLayer is different than the previous One - Test")
    @Test
    void nextPLayerIsDifferentThanThePreviousOneTest() {
        for (int i = 0; i < Player.values().length; i++) {
            Player currentPlayer = playerTurnTracker.getCurrentPlayer();
            assertNotEquals(currentPlayer, playerTurnTracker.nextPLayer());
        }
    }

    @Test
    @DisplayName("After reset current player is not Null and has value PLAYER_X - Test")
    void currentPlayerIsNotNullAndIsPLAYER_XAfterResetTest() {
        playerTurnTracker.reset();
        assertNotNull(playerTurnTracker.getCurrentPlayer());
        assertEquals(playerTurnTracker.getCurrentPlayer(), PLAYER_X);
    }
}