package Model;

import javafx.scene.control.Button;

public class Tile {
    private Button button;
    private String color;
    private String shape;
    private int row;
    private int col;
    private int index;


    public Tile(Button button, int row, int col, int index) {
        this.button = button;
        this.color = null;
        this.shape = null;
        this.row = row;
        this.col = col;
        this.index = index;
    }

    public void printTile() {

    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }
}
