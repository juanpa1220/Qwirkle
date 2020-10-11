package Model;

import Control.GameWindowController;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;

public class Board {
    private final ArrayList<Tile> board = new ArrayList<>();
    private final GridPane gridPane;

    public Board(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void newBoard() {
        int index = 0;
        for (int row = 0; row < 16; row++) {
            for (int col = 0; col < 16; col++) {
                Button temButton = new Button();
                temButton.setMaxHeight(35);
                temButton.setMinHeight(35);
                temButton.setPrefHeight(35);
                temButton.setMaxWidth(35);
                temButton.setMinWidth(35);
                temButton.setPrefWidth(35);
                temButton.setStyle("-fx-background-radius: 0;");
                temButton.setId(String.valueOf(index));
                temButton.setOnAction(this::selectedTile);
                gridPane.add(temButton, col, row);
                this.board.add(new Tile(temButton, row, col, index));
                index++;
            }
        }
    }

    public void selectedTile(ActionEvent actionEvent) {
        final Button seletedButton = (Button) actionEvent.getSource();
        if (GameWindowController.selectedTile != null && !this.board.get(Integer.parseInt(seletedButton.getId())).isBusy()) {
            this.board.get(Integer.parseInt(seletedButton.getId())).setShape(GameWindowController.selectedTile.getShape());
            this.board.get(Integer.parseInt(seletedButton.getId())).setColor(GameWindowController.selectedTile.getColor());
            this.board.get(Integer.parseInt(seletedButton.getId())).setBusy(true);
            GameWindowController.selectedTile = null;
        }
    }

    public Tile getTile(int index) {
        return board.get(index);
    }

    public ArrayList<Tile> getBoard() {
        return board;
    }

    public void restartBoard() {
        this.board.clear();
        this.newBoard();
    }
}
