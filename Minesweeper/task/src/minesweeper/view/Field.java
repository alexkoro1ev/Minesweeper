package minesweeper.view;

import minesweeper.model.Cell;
import minesweeper.util.Console;

import java.util.Random;

public class Field {
    private final Cell[][] grid;
    private final int minesAmount;

    public Field(int height, int width, int minesAmount) {
        this.minesAmount = minesAmount;
        this.grid = new Cell[height][width];


        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                grid[y][x] = new Cell(y, x, false);
            }
        }

        generateMines();
        generateNumGrid();
    }

    private void generateMines() {
        Random random = new Random();

        for (int i = 0; i < minesAmount; ) {
            int y = random.nextInt(grid.length);
            int x = random.nextInt(grid[0].length);
//            boolean isMine = false;
            if (!grid[y][x].isMine()) {
                grid[y][x].setMine(true);
                i++;
            }
        }
    }

    private void generateNumGrid() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[0].length; x++) {
                for (int a = -1; a < 2; a++) {
                    for (int b = -1; b < 2; b++) {
                        try {
                            if (grid[y + a][x + b].isMine() && (a != 0 || b != 0)) {
                                //                            isMine = true;
                                grid[y][x].incrementMineCounter();
                            }
                        } catch (IndexOutOfBoundsException e) {
                            // ignore
                        }
                    }
                }
            }
        }
    }

    public void drawField() {
        for (int a = 0; a < grid.length + 3; a++) {
            for (int c = 0; c < grid[0].length + 3; c++) {

                // draw utility grid with row/column nums
                if (a == 0 && c == 0) {
                    Console.write(" ");
                } else if (c == 1 || c == grid[0].length + 2) {
                    Console.write("|");
                } else if (a == 1 || a == grid.length + 2) {
                    Console.write("-");
                } else if (a == 0) {
                    Console.write(String.valueOf(c - 1));
                } else if (c == 0) {
                    Console.write(String.valueOf(a - 1));
                }

                // draw main grid
                else {
                    Console.write(grid[a - 2][c - 2].getLabel());
                }

            }
            Console.writeLine("");
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }


}
