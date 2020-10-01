package Model;

import java.util.ArrayList;

public class BasicSolver {
    private final Board board;

    public BasicSolver(Board board) {
        this.board = board;
    }


    public void permute(ArrayList<Tile> hand, int k) {
        for (int i = k; i < hand.size(); i++) {

            java.util.Collections.swap(hand, i, k);
            permute(hand, k + 1);
            java.util.Collections.swap(hand, k, i);

        }
        if (k == hand.size() - 1) {
            System.out.println(java.util.Arrays.toString(hand.toArray()));
        }
    }


    public void Solver(ArrayList<Tile> hand) {
        SolverAux(this.getPossiblePositions(), hand, new ArrayList<>(), 0);
    }


    private ArrayList<Tile> SolverAux(ArrayList<Tile> possiblePositions, ArrayList<Tile> hand, ArrayList<Tile> solution, int points) {
        System.out.println("hand length: " + hand.size());
        if (hand.size() == 0) {
            // revisar que sea la mejor solucion
            System.out.println("solution:" + solution);
            return solution;
        }
        for (Tile handTile : hand) {
            System.out.println(handTile.getShape() + handTile.getColor());

            int unCompatible = 0;
            if (isCompatible(handTile, possiblePositions)) {
                System.out.println("compatible: " + handTile.getShape() + handTile.getColor());

                Tile temTile = this.getPossiblePositions(handTile, possiblePositions).get(0);
                temTile.setColor(handTile.getColor());
                temTile.setShape(handTile.getShape());
                solution.add(temTile);
                possiblePositions.remove(0);
                return this.SolverAux(possiblePositions, hand, solution, points);

//                for (Tile solutionTile : this.getPossiblePositions(handTile, possiblePositions)) {
//                    solutionTile.setColor(handTile.getColor());
//                    solutionTile.setShape(handTile.getShape());
//                    solution.add(solutionTile);
//                    possiblePositions.remove(solutionTile);
//                    return this.SolverAux(possiblePositions, hand, solution, points);
//                }
            } else {
                unCompatible += 1;
                if (unCompatible == hand.size()) {
                    System.out.println("solution en el else:" + solution);
                    return this.SolverAux(possiblePositions, new ArrayList<>(), solution, points);
                } else {
                    hand.remove(handTile);// intercambia el tile
//                        hand.add(handTile);
                    return this.SolverAux(possiblePositions, hand, solution, points);
                }
            }
        }

        System.out.println("last return: " + solution);
        return solution;
    }


    /**
     * @param tile
     * @return
     */
    public boolean isCompatible(Tile tile, ArrayList<Tile> board) {
        for (Tile boardTile : board) {
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
    public ArrayList<Tile> getPossiblePositions(Tile tile, ArrayList<Tile> board) {
        ArrayList<Tile> temPossiblePositions = new ArrayList<>();
        for (Tile possibleTile : board) {
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
