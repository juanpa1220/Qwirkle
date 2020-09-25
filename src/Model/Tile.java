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

    }

}
