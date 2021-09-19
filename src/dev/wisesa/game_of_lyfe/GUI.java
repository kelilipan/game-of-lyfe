package dev.wisesa.game_of_lyfe;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    GUI() {
        super("Game Of Life");
        Grid grid = new Grid(400, 10, 0.3);
        grid.setBackground(Color.white);
        add(grid);
        setSize(400, 400);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
