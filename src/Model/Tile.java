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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        if (this.button != null) {
            this.button.getStyleClass().add(color);
        }
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
        if (this.button != null) {
            this.button.setText(shape);
        }
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public int getIndex() {
        return index;
    }
}
