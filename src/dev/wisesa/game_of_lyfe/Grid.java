package dev.wisesa.game_of_lyfe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grid extends JPanel implements ActionListener {
    private final int cellSize;

    private Cell[][] cells;
    private final int nCells;
    private int generation = 0;

    /*
     * Grid class to draw all cells
     * @param dimension of the Grid
     * @param cellSize the size or the dimension of each cell
     * @param initLifeProba probability of a cell to live at initialization
     * @param delay of frame update
     * */
    Grid(int dimension, int cellSize, double initLifeProba, int delay) {
        this.setPreferredSize(new Dimension(dimension, dimension));
        this.setBackground(Color.white);
        this.cellSize = cellSize;
        this.nCells = (dimension / cellSize);
        this.cells = new Cell[this.nCells][this.nCells];
        this.generateCells(initLifeProba);

        Timer timer = new Timer(60, this);
        timer.start();
    }

    /*
     * Function to randomly initialize cells
     * @param initLifeProba probability of a cell to live at initialization
     * */
    public void generateCells(double initLifeProba) {
        for (int row = 0; row < this.nCells; row++) {
            for (int col = 0; col < this.nCells; col++) {
                /*
                 * you can messing around with this
                 * */

//                boolean proba = Math.random() < 0.2;

                boolean randomState = row % (this.nCells / 8) == 0 || col % (this.nCells) == 0;
                Cell newCell = new Cell(row * this.cellSize, col * this.cellSize, this.cellSize, this.cellSize, randomState);
                this.cells[row][col] = newCell;
            }
        }
    }

    /*
     * Method to count neighbor of q cell and update the next state of cell
     * @param cell the cell itself
     * @return an updated cell
     * */
    public Cell updateCell(Cell cell) {
        int x = cell.x / this.cellSize;
        int y = cell.y / this.cellSize;

        int neighbor = 0;

        //Move set representing row,col
        final int[][] MOVESET = {
                {-1, -1}, {0, -1}, {1, -1},
                {-1, 0}, {1, 0},
                {-1, 1}, {0, 1}, {1, 1},
        };
        //count the neighbor
        for (int[] MOVE : MOVESET) {
            int neighborRow = x + MOVE[0];
            int neighborCol = y + MOVE[1];

            //check if the pointer is not out of bound
            if (neighborRow > 0 && neighborCol > 0 && neighborRow < this.nCells && neighborCol < this.nCells) {
                Cell neighborCell = this.cells[neighborRow][neighborCol];
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

    /*
     * Method to update all cells to next state
     * */
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
        //update current gen with next generation
        this.cells = newGeneration;
        this.generation++;
        System.out.println("GENERATION = " + this.generation);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        //paint all cells
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
