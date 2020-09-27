package Model;

import Control.GameWindowController;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private final ArrayList<Tile> board = new ArrayList<>();
    private final GridPane gridPane;

    //    private final Tile[] board = new Tile[256];
//    private final String[] shapes = {"▲", "◆", "■", "●", "★", "❈"};
//    private final String[] colors = {"green", "red", "yellow", "purple", "blue", "orange"};
//    private int seletedTile;

    public Board(GridPane gridPane) {
        this.gridPane = gridPane;

    }

    public void newBoard() {
        int index = 0;
        for (int col = 0; col < 16; col++) {
            for (int row = 0; row < 16; row++) {
                Button temButton = new Button();

//                int rdShape = new Random().nextInt(6);
//                int rdColor = new Random().nextInt(6);

                temButton.setMaxHeight(35);
                temButton.setMinHeight(35);
                temButton.setPrefHeight(35);
                temButton.setMaxWidth(35);
                temButton.setMinWidth(35);
                temButton.setPrefWidth(35);
                temButton.setStyle("-fx-background-radius: 0;");
                temButton.setId(String.valueOf(index));
                temButton.setOnAction(this::seletedTile);

//                temButton.setText(shapes[rdShape]);
//                temButton.getStyleClass().add(colors[rdColor]);

                gridPane.add(temButton, row, col);
                this.board.add(new Tile(temButton, row, col, index));
                index++;
            }
        }
    }

    public void seletedTile(ActionEvent actionEvent) {
        final Button seletedButton = (Button) actionEvent.getSource();

//        seletedButton.setText(GameWindowController.selectedShape);
//        seletedButton.getStyleClass().add(GameWindowController.selectedColor);

        String selectedShape = GameWindowController.selectedShape;
        String selectedColor = GameWindowController.selectedColor;
        if (selectedShape != null && !this.board.get(Integer.parseInt(seletedButton.getId())).isBusy()) {
            this.board.get(Integer.parseInt(seletedButton.getId())).setShape(selectedShape);
            this.board.get(Integer.parseInt(seletedButton.getId())).setColor(selectedColor);
            this.board.get(Integer.parseInt(seletedButton.getId())).setBusy(true);

            GameWindowController.selectedShape = "";
            GameWindowController.selectedColor = "";
        }
    }

    public Tile getTile(int index) {
        return board.get(index);
    }
    public ArrayList<Tile> getBoard() {
        return board;
    }
}
