package Model;

import javafx.scene.control.Button;

public class Tile {
    private Button button;
    private String color;
    private String shape;
    private int row;
    private int col;
    private int index;
    private boolean busy;


    public Tile(Button button, int row, int col, int index) {
        this.button = button;
        this.color = null;
        this.shape = null;
        this.row = row;
        this.col = col;
        this.index = index;
    }

    public Tile(String color, String shape) {
        this.button = null;
        this.color = color;
        this.shape = shape;
        this.busy = false;
    }

    public void printTile() {
        System.out.println(color + ", " + shape);

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
