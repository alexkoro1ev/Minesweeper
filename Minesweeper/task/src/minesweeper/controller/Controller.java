package minesweeper.controller;

import minesweeper.model.Cell;
import minesweeper.util.Console;
import minesweeper.view.Field;

import java.io.IOException;

public class Controller {
    private Field field;
    private boolean isFinished;
    private int minesRemain;
    private int safeCells;

    public Controller() {
    }

    public void createNewGame() throws IOException {
        this.minesRemain = getMinesAmountFromConsole();
        this.field = new Field(9, 9, minesRemain);
        this.safeCells = field.getGrid().length * field.getGrid()[0].length - minesRemain;
        isFinished = false;
        field.drawField();
    }

    public void makeAMove() throws IOException {

        String[] action;
        int[] move;
        while (true) {
            try {
                Console.write("Set/unset mine marks or claim a cell as free:");
                action = Console.readLine().split(" ");
                move = getMove(action);
                break;
            } catch (NumberFormatException e) {
                Console.writeLine("Wrong parameters. Try one more time.");
            }
        }

        makeAction(action[2], move);
        checkWin();


        field.drawField();

        if (checkWin()) {
            Console.writeLine("Congratulations! You found all mines!");
        }
    }

    private int[] getMove(String[] action) {

        int x = Integer.parseInt(action[0]);
        int y = Integer.parseInt(action[1]);

        if (action.length != 3 ||
                x < 1 || x > field.getGrid()[0].length ||
                y < 1 || y > field.getGrid().length) {
            throw new NumberFormatException();
        }
        return new int[]{x, y};
    }

    private void makeAction(String action, int[] move) {
        switch (action) {
            case "free":
                exploreCell(move);
                break;
            case "mine":
                setOrDeleteMineMark(move);
                break;
            default:
                Console.writeLine("Wrong command.");
        }
    }

    private void exploreCell(int[] move) {
        Cell cell = field.getGrid()[move[1] - 1][move[0] - 1];

        checkLose(cell);

        showAround(cell);
    }

    private void showAround(Cell cell) {
        if (cell.getMineCounter() > 0) {
            cell.show();
            --safeCells;
        } else {
            cell.show();
            --safeCells;
            for (int a = -1; a < 2; a++) {
                for (int b = -1; b < 2; b++) {
                    try {
                        int y = cell.getY() + a;
                        int x = cell.getX() + b;
                        boolean checkBorders = y > -1 && y <= field.getGrid()[0].length &&
                                x > -1 && x <= field.getGrid()[0].length;

                        if (checkBorders && !(a == 0 && b ==0) && field.getGrid()[y][x].isHidden()) {
                            showAround(field.getGrid()[y][x]);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        // ignore
                    }
                }
            }
        }
    }

    private void checkLose(Cell cell) {
        if (cell.isMine()) {
            cell.show();
            Console.writeLine("You stepped on a mine and failed!");
            isFinished = true;
        }
    }

    private int getMinesAmountFromConsole() throws IOException {
        Console.write("How many mines do you want on the field? ");
        return Console.readInt();
    }

    private void setOrDeleteMineMark(int[] move) {
        Cell cell = field.getGrid()[move[1] - 1][move[0] - 1];

        if (cell.getLabel().matches("^\\d")) {
            Console.writeLine("There is a number here!");
        } else {
            cell.setMineMark();

            if (cell.isMine()) {
                minesRemain--;
            }
        }
    }

    private boolean checkWin() {
        if (minesRemain == 0 || safeCells == 0) {
            isFinished = true;
            return true;
        }
        return false;


    }

    public boolean isFinished() {
        return isFinished;
    }
}
