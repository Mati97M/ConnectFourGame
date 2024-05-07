package dev.mmieckowski;

import javax.swing.JButton;
import java.awt.Font;

public class Cell extends JButton {
    private final int row;
    private final int col;
    private final String id;

    public Cell(int row, int col) {
        super(" ");
        this.row = row;
        this.col = col;
        this.id = (char) ('A' + col) + String.valueOf(row);
        setFont(getFont().deriveFont(Font.BOLD));
        super.setName("Button" + id);
        setFocusPainted(false);
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public String getId() {
        return id;
    }
}
