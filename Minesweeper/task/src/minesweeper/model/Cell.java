package minesweeper.model;

public class Cell {
    private final int y;
    private final int x;
    private boolean isMine;
    private boolean isHidden;
    private String label;
    private int mineCounter;

    public Cell(int y, int x, boolean isMine) {
        this.y = y;
        this.x = x;
        this.isMine = isMine;
        this.isHidden = true;
        this.label = ".";
        this.mineCounter = isMine ? -1 : 0;
    }

    public int getMineCounter() {
        return mineCounter;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean isMine() {
        return isMine;
    }

    public String getLabel() {
        return this.label;
    }

    private void updateLabel() {
        if (!isHidden) {
            if (isMine) {
                this.label = "X";
            } else if (mineCounter > 0) {
                this.label = String.valueOf(mineCounter);
            } else {
                this.label = "/";
            }
        }
    }

    public void setMine(boolean mine) {
        isMine = mine;
        mineCounter = -1;
        updateLabel();
    }

    public void incrementMineCounter() {
        mineCounter++;
    }

    public void show() {
        isHidden = false;
        updateLabel();
    }

    public void setMineMark() {
        if (label.equals("*")) {
            label = ".";
        } else if (label.equals(".")) {
            label = "*";
        }
    }

    public boolean isHidden() {
        return isHidden;
    }
}
