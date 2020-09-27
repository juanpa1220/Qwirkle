package Model;

import java.util.ArrayList;

public class BasicSolver {
    private final Board board;


    public BasicSolver(Board board) {
        this.board = board;
    }

    public void Solver(Board board, ArrayList<Tile> tilesHand, int puntos) {
        // condicion de parada: para cuando no se puedan colocar mas fichas
    }


    /**
     * @param tile
     * @return
     */
    public boolean isCompatible(Tile tile) {
        for (Tile boardTile : this.board.getBoard()) {
            if (boardTile.isBusy() && boardTile.getColor().equals(tile.getColor())) {
                return true;
            } else if (boardTile.isBusy() && boardTile.getShape().equals(tile.getShape())) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param tile
     * @return
     */
    public ArrayList<Tile> getPossiblePositions(Tile tile) {
        ArrayList<Tile> temPossiblePositions = new ArrayList<>();
        for (Tile possibleTile : this.board.getBoard()) {
            if (possibleTile.isBusy()) {
                for (Tile possibleTileAux : this.getAdjacents(possibleTile)) {
                    if (
                            !temPossiblePositions.contains(possibleTileAux) &&
                                    (possibleTile.getShape().equals(tile.getShape()) ||
                                            possibleTile.getColor().equals(tile.getColor()))
                    ) {
                        temPossiblePositions.add(possibleTileAux);
                    }
                }
            }
        }
        return temPossiblePositions;
    }

    /**
     * @return
     */
    public ArrayList<Tile> getPossiblePositions() {
        ArrayList<Tile> temPossiblePositions = new ArrayList<>();
        for (Tile tile : this.board.getBoard()) {
            if (tile.isBusy()) {
                for (Tile tile1 : this.getAdjacents(tile)) {
                    if (!temPossiblePositions.contains(tile1)) {
                        temPossiblePositions.add(tile1);
                    }
                }
            }
        }
        return temPossiblePositions;
    }

    /**
     * @param tile current tile
     * @return array list of adjacents non busy tiles
     */
    private ArrayList<Tile> getAdjacents(Tile tile) {
        ArrayList<Tile> temAdjacents = new ArrayList<>();
        if (tile.getRow() + 1 < 16 && !board.getTile(tile.getIndex() + 1).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() + 1));
        }
        if (tile.getRow() - 1 >= 0 && !board.getTile(tile.getIndex() - 1).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() - 1));
        }
        if (tile.getCol() + 1 < 16 && !board.getTile(tile.getIndex() + 16).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() + 16));
        }
        if (tile.getCol() - 1 >= 0 && !board.getTile(tile.getIndex() - 16).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() - 16));
        }
        return temAdjacents;
    }
}
