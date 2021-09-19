package dev.wisesa.game_of_lyfe;

import java.awt.*;

public class Grid extends Canvas {
    private final int dimension;
    private final int cellSize;

    private Cell[][] cells;
    private int nCells = 0;


    Grid(int dimension, int cellSize, double initLifeProba) {
        this.dimension = dimension;
        this.cellSize = cellSize;
        this.generateCells(initLifeProba);
        this.nCells = (dimension / cellSize);
        this.cells = new Cell[this.nCells][this.nCells];
        this.generateCells(initLifeProba);
    }

    public void generateCells(double initLifeProba) {
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                boolean randomState = Math.random() < initLifeProba;
                Cell newCell = new Cell(row * this.cellSize, col * this.cellSize, this.cellSize, this.cellSize, randomState);
                cells[row][col] = newCell;
            }
        }
    }

    public void nextGeneration() {
//        create array to store next generation
//        Cell[][] newGeneration = new;

    }

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
        setBackground(Color.red);
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                Cell cell = this.cells[row][col];
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
