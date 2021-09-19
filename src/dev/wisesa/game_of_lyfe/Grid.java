package dev.wisesa.game_of_lyfe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private final int cellSize;

    private Cell[][] cells;
    private final int nCells;

    Grid(int dimension, int cellSize, double initLifeProba) {
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setBackground(Color.white);
        this.cellSize = cellSize;
        this.nCells = (dimension / cellSize);
        this.cells = new Cell[this.nCells][this.nCells];
        this.generateCells(initLifeProba);

        Timer timer = new Timer(10000, this);
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

    public Cell updateCell(Cell cell) {
        int x = cell.x;
        int y = cell.y;

        int neighbor = 0;

        //Move set representing x,y
        final int[][] MOVESET = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, {1, 0},
                {-1, 1}, {0, 1}, {1, 1},
        };
        //count the neighbor
        for (int[] MOVE : MOVESET) {
            int neighborX = x + MOVE[0];
            int neighborY = y + MOVE[1];

            //check if the pointer is not out of bound
            if (neighborX > 0 && neighborY > 0 && neighborX < this.nCells && neighborY < this.nCells) {
                Cell neighborCell = this.cells[neighborX][neighborY];
                //check if the neighbor active
                if (neighborCell.isAlive) {
                    neighbor++;
                }
            }
        }

        /*
         * Any live cell with fewer than two live neighbours dies, as if by underpopulation.
         * Any live cell with two or three live neighbours lives on to the next generation.
         * Any live cell with more than three live neighbours dies, as if by overpopulation.
         * Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.
         **/
        if (cell.isAlive) {
            if (neighbor < 2) {
                //die because underpopulation
                cell.setState(false);
            } else if (neighbor > 3) {
                //die because overpopulation
                cell.setState(false);
            }
        } else {
            if (neighbor == 3) {
                //reproduction
                cell.setState(true);
            }
        }

        return cell;
    }

    public void nextGeneration() {

        //create array to store next generation
        Cell[][] newGeneration = new Cell[this.nCells][this.nCells];

        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                Cell cell = this.cells[row][col];
                Cell newCell = this.updateCell(cell);
                newGeneration[row][col] = newCell;
            }
        }
        this.cells = newGeneration;
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
        this.nextGeneration();
        this.repaint();
    }
}
