package Control;

import Model.BasicSolver;
import Model.Board;
import Model.Tile;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class GameWindowController {
    public GridPane gridPane;
    public Button tile1;
    public Button tile2;
    public Button tile3;
    public Button tile4;
    public Button tile5;
    public Button tile6;
    private final ArrayList<Tile> humanTiles = new ArrayList<Tile>();
    private final ArrayList<Tile> bag = new ArrayList<Tile>();
    private Board board;
    private BasicSolver basicSolver;
    private final String[] shapes = {"▲", "◆", "■", "●", "★", "❈"};
    private final String[] colors = {"green", "red", "yellow", "purple", "blue", "orange"};
    public static String selectedColor;
    public static String selectedShape;

    public void newGame(ActionEvent actionEvent) {
        this.board = new Board(gridPane);
        this.board.newBoard();
        this.setUpBag();
        this.setUpHumanTiles();

//        this.basicSolver = new BasicSolver(this.board);
//        for (Tile tile : this.basicSolver.getAdjacents(board.getTile(2))) {
//            System.out.println(tile.getCol() + ", " + tile.getRow());
//        }

    }

    private void setUpBag() {
        for (String color : colors) {
            for (String shape : shapes) {
                for (int i = 0; i < 3; i++) {
                    bag.add(new Tile(color, shape));
                }
            }
        }
    }

    private void setUpHumanTiles() {
        for (int i = 0; i < 6; i++) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanTiles.add(this.bag.get(rdTile));
            this.bag.remove(rdTile);
        }

        tile1.setText(humanTiles.get(0).getShape());
        tile2.setText(humanTiles.get(1).getShape());
        tile3.setText(humanTiles.get(2).getShape());
        tile4.setText(humanTiles.get(3).getShape());
        tile5.setText(humanTiles.get(4).getShape());
        tile6.setText(humanTiles.get(5).getShape());

        tile1.getStyleClass().add(humanTiles.get(0).getColor());
        tile2.getStyleClass().add(humanTiles.get(1).getColor());
        tile3.getStyleClass().add(humanTiles.get(2).getColor());
        tile4.getStyleClass().add(humanTiles.get(3).getColor());
        tile5.getStyleClass().add(humanTiles.get(4).getColor());
        tile6.getStyleClass().add(humanTiles.get(5).getColor());
    }

    public void selectedTile(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        String id = source.getId();
        if (id.equals(tile1.getId())) {
            selectedColor = humanTiles.get(0).getColor();
            selectedShape = humanTiles.get(0).getShape();
        } else if (id.equals(tile2.getId())) {
            selectedColor = humanTiles.get(1).getColor();
            selectedShape = humanTiles.get(1).getShape();
        } else if (id.equals(tile3.getId())) {
            selectedColor = humanTiles.get(2).getColor();
            selectedShape = humanTiles.get(2).getShape();
        } else if (id.equals(tile4.getId())) {
            selectedColor = humanTiles.get(3).getColor();
            selectedShape = humanTiles.get(3).getShape();
        } else if (id.equals(tile5.getId())) {
            selectedColor = humanTiles.get(4).getColor();
            selectedShape = humanTiles.get(4).getShape();
        } else if (id.equals(tile6.getId())) {
            selectedColor = humanTiles.get(5).getColor();
            selectedShape = humanTiles.get(5).getShape();
        }


        this.basicSolver = new BasicSolver(this.board);
        for (Tile tile : this.basicSolver.getPossibleTiles()) {
            System.out.println(tile.getCol() + ", " + tile.getRow());
        }
        System.out.println("--------------------------");

    }
}
