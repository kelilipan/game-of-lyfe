package dev.wisesa.game_of_lyfe;

import java.awt.*;

public class Cell extends Rectangle {
    public boolean isAlive = false;

    Cell(int x, int y, int width, int height, boolean state) {
        super(x, y, width, height);
        this.isAlive = state;
    }

    public boolean getState() {
        return isAlive;
    }

    public void setState(boolean state) {
        this.isAlive = state;
    }
}
