package dev.mmieckowski;

import static dev.mmieckowski.ConnectFourBackend.COLS;
import static dev.mmieckowski.ConnectFourBackend.ROWS;
import static dev.mmieckowski.ConnectFourBackend.SIZE;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectFour extends JFrame implements ActionListener {

    public static final String NOW_PLAYER = "Now: Player ";

    private final JLabel messenger = new JLabel();
    private final Map<String, Cell> cells = new HashMap<>();

    private final ConnectFourBackend backend;

    public ConnectFour() {
        this(false);
    }

    public ConnectFour(boolean ticTacToeMode) {
        backend = new ConnectFourBackend();
        backend.setTicTacToeMode(ticTacToeMode);
        initWindow();
        initBoardSection();
        initMessenger();
        initResetButton();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object clicked = e.getSource();
        if (clicked instanceof ButtonReset) {
            backend.restartGame();
            restartGameUI();
            return;
        }
        if (backend.isGameOver()) {
            System.out.println("Game is over. Click RESET to restart");
            return;
        }
        Cell currCell = (Cell) clicked;
        int currRow = ROWS - currCell.getRow();
        int currCol = currCell.getCol();

        try {
            PlaceTokenResult result = backend.placeToken(currRow, currCol);
            // updateUI
            if (result.isSuccess()) {
                handlePlaceTokenResult(result);
            } else if (result.getErrorMessage().isPresent()) {
                System.out.println(result.getErrorMessage().get());
            }
        } catch (InvalidPlayerSymbolException exception) {
            exception.printStackTrace();
            dispose();
        }

    }


    private void handlePlaceTokenResult(PlaceTokenResult result) {
        Cell currCell = cells.get(getCellID(result.getActualRow(), result.getActualCol()));
        char playerSymbol = result.getPlayer().getSymbol();
        currCell.setText(String.valueOf(playerSymbol));
        System.out.println(playerSymbol + " on " + currCell.getName());
        if (!backend.isGameOver()) {
            nextTurnOnMessenger();
        } else {
            endGameUI(result);
        }
    }

    private void initResetButton() {
        ButtonReset resetButton = new ButtonReset();
        add(resetButton);
        resetButton.addActionListener(this);
        resetButton.setVisible(true);
    }

    private void initMessenger() {
        messenger.setFont(getFont().deriveFont(Font.BOLD));
        messenger.setText(NOW_PLAYER + backend.getCurrentPlayer().getSymbol());
        add(messenger);
        messenger.setVisible(true);
    }

    private void initBoardSection() {
        JPanel boardSection = new JPanel(new GridLayout(ROWS, COLS, 0, 0));
        boardSection.setSize(COLS * SIZE, ROWS * SIZE);
        initBoard(boardSection);
        add(boardSection);
    }

    private void initBoard(JPanel boardSection) {
        backend.restartBoard();
        for (int r = ROWS; r > 0; r--) {
            for (int c = 0; c < COLS; c++) {
                Cell tmpCell = new Cell(r, c);
                initCell(boardSection, tmpCell);
                cells.put(tmpCell.getId(), tmpCell);
            }
        }
        boardSection.setVisible(true);
    }

    private void initWindow() {
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(COLS * SIZE, (ROWS + 1) * SIZE);
        setTitle("Connect Four");
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setVisible(true);
    }


    private void initCell(JPanel boardSection, Cell tmpCell) {
        tmpCell.setBackground(Color.LIGHT_GRAY);
        tmpCell.setOpaque(true);
        tmpCell.setVisible(true);
        tmpCell.addActionListener(this);
        boardSection.add(tmpCell);
    }


    private void nextTurnOnMessenger() {
        messenger.setText(NOW_PLAYER + backend.nextPlayer().getSymbol());
    }

    private void endGameUI(PlaceTokenResult result) {
        markVictoryCells(result.getVictoryCells());
        char playerSymbol = ' ';
        if (result.isSuccess() && result.isGameOver()) {
            playerSymbol = result.getPlayer().getSymbol();
        }
        String gameOverMsg = "Winner: " + playerSymbol;
        System.out.println(gameOverMsg);
        messenger.setText(gameOverMsg);
    }


    private void markVictoryCells(List<int[]> victoryIndexes) {
        for (int[] rowCol : victoryIndexes) {
            Cell currCell = cells.get(getCellID(rowCol[0], rowCol[1]));
            currCell.setBackground(Color.GREEN);
        }
    }


    private void restartGameUI() {
        for (Cell cell : cells.values()) {
            cell.setText(null);
            cell.setBackground(Color.LIGHT_GRAY);
        }
        messenger.setText(NOW_PLAYER + backend.getCurrentPlayer().getSymbol());
    }

    private static String getCellID(int currRow, int currCol) {
        return (char) ('A' + currCol) + String.valueOf(ROWS - currRow);
    }


}