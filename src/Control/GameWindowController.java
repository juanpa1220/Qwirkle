package Control;

import Model.Board;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;

public class GameWindowController {
    public GridPane gridPane;
    private String selectedColor;
    private String selectedShape;

    public GameWindowController() {
    }

    public void newGame(ActionEvent actionEvent) {
        new Board(gridPane).newBoard();
    }

}
