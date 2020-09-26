package Model;

import java.util.ArrayList;

public class BasicSolver {
    private Board board;


    public BasicSolver(Board board) {
        this.board = board;
    }

    public void Solver(Board board, ArrayList<Tile> tilesHand) {

    }
    
    public ArrayList<Tile> getPossibleTiles() {
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
    public ArrayList<Tile> getAdjacents(Tile tile) {
        ArrayList<Tile> temAdjacents = new ArrayList<>();
        if (tile.getRow() + 1 < 16 && !board.getTile(tile.getIndex() + 1).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() + 1));
        }
        if (tile.getRow() - 1 > 0 && !board.getTile(tile.getIndex() - 1).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() - 1));
        }
        if (tile.getCol() + 1 < 16 && !board.getTile(tile.getIndex() + 16).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() + 16));
        }
        if (tile.getCol() - 1 > 0 && !board.getTile(tile.getIndex() - 16).isBusy()) {
            temAdjacents.add(board.getTile(tile.getIndex() - 16));
        }
        return temAdjacents;
    }

}
