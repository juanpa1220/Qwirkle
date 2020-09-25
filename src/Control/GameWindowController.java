package Control;

import Model.Board;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;

import java.awt.*;

public class GameWindowController {
    public GridPane gridPane;
    private String selectedColor;
    private String selectedShape;

    public GameWindowController() {
    }

    public void newGame(ActionEvent actionEvent) {
        new Board(gridPane).newBoard();
    }

    public void test(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        String id = source.getId();

        System.out.println(id);
    }
}
