package Model;

import javafx.scene.control.Button;

public class Tile {
    private Button button;
    private String color;
    private String shape;
    private int row;
    private int col;

    public Tile(Button button, int row, int col) {
        this.button = button;
        this.color = null;
        this.shape = null;
        this.row = row;
        this.col = col;
    }

    public void printTile() {
        this.button.setStyle("fx-background-color: #4ecf09");
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
}
