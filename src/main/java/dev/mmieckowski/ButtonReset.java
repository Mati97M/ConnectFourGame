package dev.mmieckowski;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;

public class ButtonReset extends JButton {
    public ButtonReset() {
        setName("ButtonReset");
        setFont(getFont().deriveFont(Font.BOLD));
        setBackground(Color.RED);
        setOpaque(true);
        setFocusPainted(false);
        setText("RESET");
        setForeground(Color.ORANGE);
        setVisible(true);
    }
}
