package dev.mmieckowski;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Referee")
class RefereeTest {
    Referee referee;

    @Test
    @DisplayName("Game over when four horizontally - Test")
    void gameOverWhenFourHorizontallyTest() {
        referee = new Referee(BoardConfigurations.FOUR_HORIZONTALLY);

        assertTrue(referee.isGameOver(2, 0));
    }

    @Test
    @DisplayName("Game over when four vertically - Test")
    void gameOverWhenFourVerticallyTest() {
        referee = new Referee(BoardConfigurations.FOUR_VERTICALLY);

        assertTrue(referee.isGameOver(0, 2));
    }

    @Test
    @DisplayName("Game over when four diagonally primary - Test")
    void gameOverWhenFourDiagonallyPrimaryTest() {
        referee = new Referee(BoardConfigurations.FOUR_DIAGONALLY_PRIMARY);

        assertTrue(referee.isGameOver(1, 0));
    }

    @Test
    @DisplayName("Game over when four diagonally secondary - Test")
    void gameOverWhenFourDiagonallySecondaryTest() {
        referee = new Referee(BoardConfigurations.FOUR_DIAGONALLY_SECONDARY);

        assertTrue(referee.isGameOver(0, 4));
    }

    @Test
    @DisplayName("No Game over when no sequence - Test")
    void noGameOverWhenNoSequenceTest() {
        referee = new Referee(BoardConfigurations.NO_SEQUENCE);

        assertFalse(referee.isGameOver(2, 1));
    }

    @Test
    @DisplayName("No victory indexes when no sequence - Test")
    void noVictoryIndexesWhenNoSequenceTest() {
        referee = new Referee(BoardConfigurations.NO_SEQUENCE);

        referee.isGameOver(2, 1);

        assertTrue(referee.getVictoryIndexes().isEmpty());
    }

    @Test
    @DisplayName("No Victory Indexes after reset - Test")
    void resetTest() {
        referee = new Referee(new char[][]{{}});

        referee.reset();

        assertTrue(referee.getVictoryIndexes().isEmpty());
    }
}