package dev.wisesa.game_of_lyfe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private final int dimension;
    private final int cellSize;
    private final double initLifeProba;

    private Cell[][] cells;
    private int nCells;

    private Timer timer;

    Grid(int dimension, int cellSize, double initLifeProba) {
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setBackground(Color.white);
        this.dimension = dimension;
        this.cellSize = cellSize;
        this.nCells = (dimension / cellSize);
        this.cells = new Cell[this.nCells][this.nCells];
        this.initLifeProba = initLifeProba;
        this.generateCells(initLifeProba);

        this.timer = new Timer(80, this);
        timer.start();
    }

    public void generateCells(double initLifeProba) {
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                boolean randomState = Math.random() < initLifeProba;
                Cell newCell = new Cell(row * this.cellSize, col * this.cellSize, this.cellSize, this.cellSize, randomState);
                this.cells[row][col] = newCell;
            }
        }
    }

    public void nextGeneration() {
//        create array to store next generation
        Cell[][] newGeneration = new Cell[this.nCells][this.nCells];

    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                Cell cell = this.cells[row][col];
                if (cell.isAlive) {
                    graphics.setColor(Color.black);
                } else {
                    graphics.setColor(Color.white);
                }
                graphics.fillRect(cell.x, cell.y, cell.width, cell.height);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.generateCells(this.initLifeProba);
        this.repaint();
    }
}
