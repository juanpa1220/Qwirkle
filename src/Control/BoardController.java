package Control;

import Model.Tile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class BoardController {
    public GridPane gridPane;
    private String selectedColor;
    private String selectedShape;
    private final Tile[] board = new Tile[256];

    public BoardController() {

    }

    public void newGame(ActionEvent actionEvent) {
        int index = 0;
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                Button temButton = new Button();
                temButton.setText(String.valueOf(index));
                temButton.setMaxHeight(25);
                temButton.setMinHeight(25);
                temButton.setPrefHeight(25);
                temButton.setMaxWidth(25);
                temButton.setMinWidth(25);
                temButton.setPrefWidth(25);
                temButton.setMnemonicParsing(false);
                this.gridPane.add(temButton, row, col);
                this.board[index] = new Tile(temButton, row, col);
//                System.out.println(index);
//                System.out.println(this.board[index].getRow() + ", " + this.board[index].getCol());
                index++;
            }
        }

    }
}
