package dev.wisesa.game_of_lyfe;

import java.awt.*;

public class Grid extends Canvas {
    private int dimension;
    private int cellSize;

    private Cell[][] cells;

    private double initLifeProba = 0.5;

    Grid(int dimension, int cellSize) {
        this.dimension = dimension;
        this.cellSize = cellSize;
        int nCells = dimension / cellSize;
        this.cells = new Cell[nCells][nCells];
        for (int row = 0; row < nCells; row++) {
            for (int col = 0; col < nCells; col++) {
                boolean randomState = Math.random() < initLifeProba;
                Cell newCell = new Cell(row * this.cellSize, col * this.cellSize, this.cellSize, this.cellSize, randomState);
                cells[row][col] = newCell;
            }
        }
    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        setBackground(Color.red);
        int nCells = dimension / cellSize;
        for (int row = 0; row < nCells; row++) {
            for (int col = 0; col < nCells; col++) {
                Cell cell = cells[row / 20][col / 20];
                System.out.println(cell.isAlive);
                System.out.println(cell.x);
                System.out.println(cell.y);
                if (cell.isAlive) {
                    graphics.setColor(Color.black);
                } else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(cell.x, cell.y, cell.width, cell.height);
            }
        }
    }
}
