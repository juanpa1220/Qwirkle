package Model;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.Random;

public class Board {
    private final Tile[] board = new Tile[256];
    private final GridPane gridPane;
    private final String[] shapes = {"▲", "◆", "■", "●", "★", "❈"};
    private final String[] colors = {"green", "red", "yellow", "purple", "blue", "orange"};

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
//                                "-fx-text-fill: #3e3c3c;" +
//                                "-fx-border-color: #a42727;" +
//                                "-fx-background-color: \n" +
//                                "        linear-gradient(#686868 0%, #232723 25%, #373837 75%, #757575 100%),\n" +
//                                "        linear-gradient(#020b02, #3a3a3a),\n" +
//                                "        linear-gradient(#b9b9b9 0%, #c2c2c2 20%, #afafaf 80%, #c8c8c8 100%),\n" +
//                                "        linear-gradient(#f5f5f5 0%, #dbdbdb 50%, #cacaca 51%, #d7d7d7 100%);"
                );

                temButton.setText(shapes[rdShape]);
                temButton.getStyleClass().add(colors[rdColor]);

                gridPane.add(temButton, row, col);
                this.board[index] = new Tile(temButton, row, col);
                index++;
            }
        }
    }
}
