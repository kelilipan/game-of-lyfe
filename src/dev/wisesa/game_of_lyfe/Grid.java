package dev.wisesa.game_of_lyfe;

import java.awt.*;

public class Grid extends Canvas {
    @Override
    public void paint(Graphics graphics) {
        graphics.setColor(Color.red);
        graphics.setFont(new Font("Bold", Font.BOLD, 20));
        graphics.drawString("Hello world", 100, 100);
    }
}
