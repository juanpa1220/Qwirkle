package Model;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Board {
    private final Tile[] board = new Tile[256];
    private final GridPane gridPane;
    private final String[] shapes = {"▲", "◆", "■", "●", "★", "❈"};
    private final String[] colors = {"green", "red", "yellow", "purple", "blue", "orange"};
    private int seletedTile;

    public Board(GridPane gridPane) {
        this.gridPane = gridPane;
        this.newBoard();
    }

    public void newBoard() {
        int index = 0;
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                Button temButton = new Button();

                int rdShape = new Random().nextInt(6);
                int rdColor = new Random().nextInt(6);

                temButton.setMaxHeight(35);
                temButton.setMinHeight(35);
                temButton.setPrefHeight(35);
                temButton.setMaxWidth(35);
                temButton.setMinWidth(35);
                temButton.setPrefWidth(35);
                temButton.setStyle(
                        "-fx-background-radius: 0;"
                );
                temButton.setId(String.valueOf(index));
                temButton.setOnAction(this::seletedTile);

//                temButton.setText(shapes[rdShape]);
//                temButton.getStyleClass().add(colors[rdColor]);

                gridPane.add(temButton, row, col);
                this.board[index] = new Tile(temButton, row, col, index);
                index++;
            }
        }

    }

    public void seletedTile(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        String id = source.getId();
        this.seletedTile = Integer.getInteger(id);
    }

    public void getSelectedTile(){

    }

}
