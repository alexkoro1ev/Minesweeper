package minesweeper;

import minesweeper.controller.Controller;

import java.io.IOException;

public class Minesweeper {
    private final Controller controller;

    public Minesweeper() {
        this.controller = new Controller();
    }

    public void run() throws IOException {
        controller.createNewGame();
        while(!controller.isFinished()) {
            controller.makeAMove();
        }
    }
}
