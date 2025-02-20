package Control;

import Model.BasicSolver;
import Model.Board;
import Model.SmartSolver;
import Model.Tile;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Random;

public class GameWindowController {
    public GridPane gridPane;
    public Button startGame;
    public Button playButton;
    public Button tile1;
    public Button tile2;
    public Button tile3;
    public Button tile4;
    public Button tile5;
    public Button tile6;
    public Button bTile1;
    public Button bTile2;
    public Button bTile3;
    public Button bTile4;
    public Button bTile5;
    public Button bTile6;
    public Button sTile1;
    public Button sTile2;
    public Button sTile3;
    public Button sTile4;
    public Button sTile5;
    public Button sTile6;
    public Label humanPoints;
    public Label smartPoints;
    public Label basicPoints;
    private int humanIntPoints = 0;
    private int smartIntPoints = 0;
    private int basicIntPoints = 0;
    private final ArrayList<Tile> bag = new ArrayList<>();
    private final ArrayList<Tile> humanPlayerTiles = new ArrayList<>();
    private final ArrayList<Tile> basicPlayerTiles = new ArrayList<>();
    private final ArrayList<Tile> smartPlayerTiles = new ArrayList<>();
    private Board board;
    private boolean restartBoard = false;
    private final String[] shapes = {"▲", "◆", "■", "●", "★", "❈"};
    private final String[] colors = {"green", "red", "yellow", "purple", "blue", "orange"};
    private int turn;
    public static Tile selectedTile;

    public void newGame() {
        if (restartBoard) {
            this.board.restartBoard();
            this.bag.clear();
            this.setUpBag();
            this.humanPlayerTiles.clear();
            this.setUpHumanPlayerTiles();
            this.basicPlayerTiles.clear();
            this.setUpBasicPlayerTiles();
            this.smartPlayerTiles.clear();
            this.setUpSmartPlayerTiles();
            this.turn = 0;
            this.humanPoints.setText(String.valueOf(0));
            this.basicPoints.setText(String.valueOf(0));
            this.smartPoints.setText(String.valueOf(0));
            this.smartIntPoints = 0;
            this.humanIntPoints = 0;
            this.basicIntPoints = 0;
        } else {
            this.board = new Board(this.gridPane);
            this.board.newBoard();
            this.setUpBag();
            this.setUpHumanPlayerTiles();
            this.setUpBasicPlayerTiles();
            this.setUpSmartPlayerTiles();
            this.turn = 0;
            this.restartBoard = true;
            this.startGame.setText("   Reiniciar   ");
        }
        this.tile1.setDisable(false);
        this.tile2.setDisable(false);
        this.tile3.setDisable(false);
        this.tile4.setDisable(false);
        this.tile5.setDisable(false);
        this.tile6.setDisable(false);
        this.playButton.setDisable(false);
    }

    private void setUpBag() {
        for (String color : this.colors) {
            for (String shape : this.shapes) {
                for (int i = 0; i < 3; i++) {
                    this.bag.add(new Tile(color, shape));
                }
            }
        }
    }

    private void setUpHumanPlayerTiles() {
        for (int i = 0; i < 6; i++) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.add(this.bag.get(rdTile));
            this.bag.remove(rdTile);
        }
        this.tile1.setText(this.humanPlayerTiles.get(0).getShape());
        this.tile2.setText(this.humanPlayerTiles.get(1).getShape());
        this.tile3.setText(this.humanPlayerTiles.get(2).getShape());
        this.tile4.setText(this.humanPlayerTiles.get(3).getShape());
        this.tile5.setText(this.humanPlayerTiles.get(4).getShape());
        this.tile6.setText(this.humanPlayerTiles.get(5).getShape());
        if (restartBoard) {
            this.tile1.getStyleClass().set(this.tile1.getStyleClass().size() - 1, this.humanPlayerTiles.get(0).getColor());
            this.tile2.getStyleClass().set(this.tile2.getStyleClass().size() - 1, this.humanPlayerTiles.get(1).getColor());
            this.tile3.getStyleClass().set(this.tile3.getStyleClass().size() - 1, this.humanPlayerTiles.get(2).getColor());
            this.tile4.getStyleClass().set(this.tile4.getStyleClass().size() - 1, this.humanPlayerTiles.get(3).getColor());
            this.tile5.getStyleClass().set(this.tile5.getStyleClass().size() - 1, this.humanPlayerTiles.get(4).getColor());
            this.tile6.getStyleClass().set(this.tile6.getStyleClass().size() - 1, this.humanPlayerTiles.get(5).getColor());
        } else {
            this.tile1.getStyleClass().add(this.humanPlayerTiles.get(0).getColor());
            this.tile2.getStyleClass().add(this.humanPlayerTiles.get(1).getColor());
            this.tile3.getStyleClass().add(this.humanPlayerTiles.get(2).getColor());
            this.tile4.getStyleClass().add(this.humanPlayerTiles.get(3).getColor());
            this.tile5.getStyleClass().add(this.humanPlayerTiles.get(4).getColor());
            this.tile6.getStyleClass().add(this.humanPlayerTiles.get(5).getColor());
        }
    }

    private void setUpBasicPlayerTiles() {
        for (int i = 0; i < 6; i++) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.basicPlayerTiles.add(this.bag.get(rdTile));
            this.bag.remove(rdTile);
        }
        this.bTile1.setText(this.basicPlayerTiles.get(0).getShape());
        this.bTile2.setText(this.basicPlayerTiles.get(1).getShape());
        this.bTile3.setText(this.basicPlayerTiles.get(2).getShape());
        this.bTile4.setText(this.basicPlayerTiles.get(3).getShape());
        this.bTile5.setText(this.basicPlayerTiles.get(4).getShape());
        this.bTile6.setText(this.basicPlayerTiles.get(5).getShape());
        if (restartBoard) {
            this.bTile1.getStyleClass().set(this.bTile1.getStyleClass().size() - 1, this.basicPlayerTiles.get(0).getColor());
            this.bTile2.getStyleClass().set(this.bTile2.getStyleClass().size() - 1, this.basicPlayerTiles.get(1).getColor());
            this.bTile3.getStyleClass().set(this.bTile3.getStyleClass().size() - 1, this.basicPlayerTiles.get(2).getColor());
            this.bTile4.getStyleClass().set(this.bTile4.getStyleClass().size() - 1, this.basicPlayerTiles.get(3).getColor());
            this.bTile5.getStyleClass().set(this.bTile5.getStyleClass().size() - 1, this.basicPlayerTiles.get(4).getColor());
            this.bTile6.getStyleClass().set(this.bTile6.getStyleClass().size() - 1, this.basicPlayerTiles.get(5).getColor());
        } else {
            this.bTile1.getStyleClass().add(this.basicPlayerTiles.get(0).getColor());
            this.bTile2.getStyleClass().add(this.basicPlayerTiles.get(1).getColor());
            this.bTile3.getStyleClass().add(this.basicPlayerTiles.get(2).getColor());
            this.bTile4.getStyleClass().add(this.basicPlayerTiles.get(3).getColor());
            this.bTile5.getStyleClass().add(this.basicPlayerTiles.get(4).getColor());
            this.bTile6.getStyleClass().add(this.basicPlayerTiles.get(5).getColor());
        }
    }

    private void setUpSmartPlayerTiles() {
        for (int i = 0; i < 6; i++) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.smartPlayerTiles.add(this.bag.get(rdTile));
            this.bag.remove(rdTile);
        }
        this.sTile1.setText(this.smartPlayerTiles.get(0).getShape());
        this.sTile2.setText(this.smartPlayerTiles.get(1).getShape());
        this.sTile3.setText(this.smartPlayerTiles.get(2).getShape());
        this.sTile4.setText(this.smartPlayerTiles.get(3).getShape());
        this.sTile5.setText(this.smartPlayerTiles.get(4).getShape());
        this.sTile6.setText(this.smartPlayerTiles.get(5).getShape());
        if (restartBoard) {
            this.sTile1.getStyleClass().set(this.sTile1.getStyleClass().size() - 1, this.smartPlayerTiles.get(0).getColor());
            this.sTile2.getStyleClass().set(this.sTile2.getStyleClass().size() - 1, this.smartPlayerTiles.get(1).getColor());
            this.sTile3.getStyleClass().set(this.sTile3.getStyleClass().size() - 1, this.smartPlayerTiles.get(2).getColor());
            this.sTile4.getStyleClass().set(this.sTile4.getStyleClass().size() - 1, this.smartPlayerTiles.get(3).getColor());
            this.sTile5.getStyleClass().set(this.sTile5.getStyleClass().size() - 1, this.smartPlayerTiles.get(4).getColor());
            this.sTile6.getStyleClass().set(this.sTile6.getStyleClass().size() - 1, this.smartPlayerTiles.get(5).getColor());
        } else {
            this.sTile1.getStyleClass().add(this.smartPlayerTiles.get(0).getColor());
            this.sTile2.getStyleClass().add(this.smartPlayerTiles.get(1).getColor());
            this.sTile3.getStyleClass().add(this.smartPlayerTiles.get(2).getColor());
            this.sTile4.getStyleClass().add(this.smartPlayerTiles.get(3).getColor());
            this.sTile5.getStyleClass().add(this.smartPlayerTiles.get(4).getColor());
            this.sTile6.getStyleClass().add(this.smartPlayerTiles.get(5).getColor());
        }
    }

    public void selectedTile(ActionEvent actionEvent) {
        final Node source = (Node) actionEvent.getSource();
        String id = source.getId();
        if (id.equals(this.tile1.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(0);
            this.tile1.setDisable(true);
        } else if (id.equals(this.tile2.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(1);
            this.tile2.setDisable(true);
        } else if (id.equals(this.tile3.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(2);
            this.tile3.setDisable(true);
        } else if (id.equals(this.tile4.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(3);
            this.tile4.setDisable(true);
        } else if (id.equals(this.tile5.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(4);
            this.tile5.setDisable(true);
        } else if (id.equals(this.tile6.getId())) {
            if (selectedTile != null) {
                this.enableTile();
            }
            selectedTile = this.humanPlayerTiles.get(5);
            this.tile6.setDisable(true);
        }
    }

    private void enableTile() {
        if (selectedTile == this.humanPlayerTiles.get(0)) {
            this.tile1.setDisable(false);
        } else if (selectedTile == this.humanPlayerTiles.get(1)) {
            this.tile2.setDisable(false);
        } else if (selectedTile == this.humanPlayerTiles.get(2)) {
            this.tile3.setDisable(false);
        } else if (selectedTile == this.humanPlayerTiles.get(3)) {
            this.tile4.setDisable(false);
        } else if (selectedTile == this.humanPlayerTiles.get(4)) {
            this.tile5.setDisable(false);
        } else if (selectedTile == this.humanPlayerTiles.get(5)) {
            this.tile6.setDisable(false);
        }
    }

    public void play() {
        if (this.turn == 0) {
            // human turn
            if (selectedTile != null) {
                enableTile();
            }
            this.setNewHumanPlayerTiles();
            this.humanPoints.setText(String.valueOf(this.humanIntPoints));
            this.tile1.setDisable(true);
            this.tile2.setDisable(true);
            this.tile3.setDisable(true);
            this.tile4.setDisable(true);
            this.tile5.setDisable(true);
            this.tile6.setDisable(true);
            this.turn++;
        } else if (this.turn == 1) {
            // smart solver turn
            SmartSolver smartSolver = new SmartSolver(this.board);

            ArrayList<Tile> solution = smartSolver.solve(this.smartPlayerTiles);
            long tiempo1 = System.currentTimeMillis();
            for (Tile solutionTile : solution) {
                this.board.getTile(solutionTile.getIndex()).setColor(solutionTile.getColor());
                this.board.getTile(solutionTile.getIndex()).setShape(solutionTile.getShape());
                this.board.getTile(solutionTile.getIndex()).setBusy(true);
            }

            this.smartIntPoints += smartSolver.getMaxPoints();
            this.smartPoints.setText(String.valueOf(this.smartIntPoints));
            this.setNewSmartPlayerTiles(solution);
            long tiempo2 = System.currentTimeMillis();
            System.out.println(String.valueOf(tiempo2 - tiempo1));
//            System.out.println(smartSolver.getMaxPoints());


            this.turn++;
        } else if (this.turn == 2) {

            // basic solver turn
            BasicSolver basicSolver = new BasicSolver(this.board);
            ArrayList<Tile> solution = basicSolver.solve(this.basicPlayerTiles);
            for (Tile solutionTile : solution) {
                this.board.getTile(solutionTile.getIndex()).setColor(solutionTile.getColor());
                this.board.getTile(solutionTile.getIndex()).setShape(solutionTile.getShape());
                this.board.getTile(solutionTile.getIndex()).setBusy(true);
            }
            this.basicIntPoints += basicSolver.getMaxPoints();
            this.basicPoints.setText(String.valueOf(this.basicIntPoints));
            this.setNewBasicPlayerTiles(solution);

            this.tile1.setDisable(false);
            this.tile2.setDisable(false);
            this.tile3.setDisable(false);
            this.tile4.setDisable(false);
            this.tile5.setDisable(false);
            this.tile6.setDisable(false);
            this.turn = 0;
        }
    }

    private void setNewBasicPlayerTiles(ArrayList<Tile> solution) {
        for (Tile tile : solution) {
            if (tile.getColor().equals(basicPlayerTiles.get(0).getColor()) && tile.getShape().equals(basicPlayerTiles.get(0).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(0, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile1.setText(this.basicPlayerTiles.get(0).getShape());
                this.bTile1.getStyleClass().set(this.bTile1.getStyleClass().size() - 1, this.basicPlayerTiles.get(0).getColor());
            } else if (tile.getColor().equals(basicPlayerTiles.get(1).getColor()) && tile.getShape().equals(basicPlayerTiles.get(1).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(1, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile2.setText(this.basicPlayerTiles.get(1).getShape());
                this.bTile2.getStyleClass().set(this.bTile2.getStyleClass().size() - 1, this.basicPlayerTiles.get(1).getColor());
            } else if (tile.getColor().equals(basicPlayerTiles.get(2).getColor()) && tile.getShape().equals(basicPlayerTiles.get(2).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(2, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile3.setText(this.basicPlayerTiles.get(2).getShape());
                this.bTile3.getStyleClass().set(this.bTile3.getStyleClass().size() - 1, this.basicPlayerTiles.get(2).getColor());
            } else if (tile.getColor().equals(basicPlayerTiles.get(3).getColor()) && tile.getShape().equals(basicPlayerTiles.get(3).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(3, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile4.setText(this.basicPlayerTiles.get(3).getShape());
                this.bTile4.getStyleClass().set(this.bTile4.getStyleClass().size() - 1, this.basicPlayerTiles.get(3).getColor());
            } else if (tile.getColor().equals(basicPlayerTiles.get(4).getColor()) && tile.getShape().equals(basicPlayerTiles.get(4).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(4, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile5.setText(this.basicPlayerTiles.get(4).getShape());
                this.bTile5.getStyleClass().set(this.bTile5.getStyleClass().size() - 1, this.basicPlayerTiles.get(4).getColor());
            } else if (tile.getColor().equals(basicPlayerTiles.get(5).getColor()) && tile.getShape().equals(basicPlayerTiles.get(5).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.basicPlayerTiles.set(5, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.bTile6.setText(this.basicPlayerTiles.get(5).getShape());
                this.bTile6.getStyleClass().set(this.bTile6.getStyleClass().size() - 1, this.basicPlayerTiles.get(5).getColor());
            }
        }
    }

    private void setNewSmartPlayerTiles(ArrayList<Tile> solution) {
        for (Tile tile : solution) {
            if (tile.getColor().equals(smartPlayerTiles.get(0).getColor()) && tile.getShape().equals(smartPlayerTiles.get(0).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(0, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile1.setText(this.smartPlayerTiles.get(0).getShape());
                this.sTile1.getStyleClass().set(this.sTile1.getStyleClass().size() - 1, this.smartPlayerTiles.get(0).getColor());
            } else if (tile.getColor().equals(smartPlayerTiles.get(1).getColor()) && tile.getShape().equals(smartPlayerTiles.get(1).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(1, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile2.setText(this.smartPlayerTiles.get(1).getShape());
                this.sTile2.getStyleClass().set(this.sTile2.getStyleClass().size() - 1, this.smartPlayerTiles.get(1).getColor());
            } else if (tile.getColor().equals(smartPlayerTiles.get(2).getColor()) && tile.getShape().equals(smartPlayerTiles.get(2).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(2, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile3.setText(this.smartPlayerTiles.get(2).getShape());
                this.sTile3.getStyleClass().set(this.sTile3.getStyleClass().size() - 1, this.smartPlayerTiles.get(2).getColor());
            } else if (tile.getColor().equals(smartPlayerTiles.get(3).getColor()) && tile.getShape().equals(smartPlayerTiles.get(3).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(3, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile4.setText(this.smartPlayerTiles.get(3).getShape());
                this.sTile4.getStyleClass().set(this.sTile4.getStyleClass().size() - 1, this.smartPlayerTiles.get(3).getColor());
            } else if (tile.getColor().equals(smartPlayerTiles.get(4).getColor()) && tile.getShape().equals(smartPlayerTiles.get(4).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(4, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile5.setText(this.smartPlayerTiles.get(4).getShape());
                this.sTile5.getStyleClass().set(this.sTile5.getStyleClass().size() - 1, this.smartPlayerTiles.get(4).getColor());
            } else if (tile.getColor().equals(smartPlayerTiles.get(5).getColor()) && tile.getShape().equals(smartPlayerTiles.get(5).getShape())) {
                int rdTile = new Random().nextInt(this.bag.size());
                this.smartPlayerTiles.set(5, this.bag.get(rdTile));
                this.bag.remove(rdTile);

                this.sTile6.setText(this.smartPlayerTiles.get(5).getShape());
                this.sTile6.getStyleClass().set(this.sTile6.getStyleClass().size() - 1, this.smartPlayerTiles.get(5).getColor());
            }
        }
    }


    private void setNewHumanPlayerTiles() {
        if (this.tile1.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(0, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile1.setText(this.humanPlayerTiles.get(0).getShape());
            this.tile1.getStyleClass().set(this.tile1.getStyleClass().size() - 1, this.humanPlayerTiles.get(0).getColor());
        }
        if (this.tile2.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(1, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile2.setText(this.humanPlayerTiles.get(1).getShape());
            this.tile2.getStyleClass().set(this.tile2.getStyleClass().size() - 1, this.humanPlayerTiles.get(1).getColor());
        }
        if (this.tile3.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(2, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile3.setText(this.humanPlayerTiles.get(2).getShape());
            this.tile3.getStyleClass().set(this.tile3.getStyleClass().size() - 1, this.humanPlayerTiles.get(2).getColor());
        }
        if (this.tile4.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(3, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile4.setText(this.humanPlayerTiles.get(3).getShape());
            this.tile4.getStyleClass().set(this.tile4.getStyleClass().size() - 1, this.humanPlayerTiles.get(3).getColor());
        }
        if (this.tile5.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(4, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile5.setText(this.humanPlayerTiles.get(4).getShape());
            this.tile5.getStyleClass().set(this.tile5.getStyleClass().size() - 1, this.humanPlayerTiles.get(4).getColor());
        }
        if (this.tile6.isDisable()) {
            int rdTile = new Random().nextInt(this.bag.size());
            this.humanPlayerTiles.set(5, this.bag.get(rdTile));
            this.bag.remove(rdTile);
            this.humanIntPoints++;

            this.tile6.setText(this.humanPlayerTiles.get(5).getShape());
            this.tile6.getStyleClass().set(this.tile6.getStyleClass().size() - 1, this.humanPlayerTiles.get(5).getColor());
        }
    }
}
