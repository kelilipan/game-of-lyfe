package dev.wisesa.game_of_lyfe;

import java.awt.*;
import java.util.ArrayList;

public class Grid extends Canvas {
    private int dimension;
    private int cellSize;

    private ArrayList<ArrayList<Cell>> cells = new ArrayList<>();

    private double initLifeProba = 0.5;

    Grid(int dimension, int cellSize) {
        this.dimension = dimension;
        this.cellSize = cellSize;

        int nCells = dimension / cellSize;
        for (int row = 0; row < nCells; row += 20) {
            for (int col = 0; col < nCells; col += 20) {
                boolean randomState = Math.random() < initLifeProba;
                Cell newCell = new Cell(row, col, this.cellSize, this.cellSize, randomState);
                cells.get(row / 20).add(newCell);
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

    }
}
