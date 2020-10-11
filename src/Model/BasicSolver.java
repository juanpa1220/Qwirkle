package Model;

import java.util.ArrayList;
import java.util.Random;

public class BasicSolver {
    private final ArrayList<Tile> board;
    private final ArrayList<ArrayList<Tile>> solutions = new ArrayList<>();
    private int maxPoints = 0;

    public BasicSolver(Board board) {
        this.board = new ArrayList<>();
        for (Tile tile : board.getBoard()) {
            this.board.add(new Tile(tile.getColor(), tile.getShape(), tile.getRow(), tile.getCol(), tile.getIndex(), tile.isBusy()));
        }
    }

    public ArrayList<Tile> solve(ArrayList<Tile> hand) {
        this.solveAux(hand, 0, this.board, 0, new ArrayList<>(), null);
//        System.out.println("------------------------AQUI");
//        for (int i = 0; i < this.solutions.size(); i++) {
//            System.out.println("solution" + i + ": " + this.solutions.get(i).toString());
//        }
        if (solutions.isEmpty()) {
            return new ArrayList<>();
        } else {
            int rdNum = new Random().nextInt(this.solutions.size());
            return this.solutions.get(rdNum);
        }
    }

    public void solveAux(ArrayList<Tile> hand, int k, ArrayList<Tile> board, int j, ArrayList<Tile> solution, Tile origen) {
        for (int i = k; i < hand.size(); i++) {
            java.util.Collections.swap(hand, i, k);
            solveAux(hand, k + 1, board, j, solution, origen);
            java.util.Collections.swap(hand, k, i);
        }
        if (k == hand.size() - 1) {
            if (j == hand.size() - 1) {
                if (origen != null && this.filterSolution(solution, board, origen) > 0) {
                    int temPoints = this.filterSolution(solution, board, origen);
                    if (solution.size() == 0) {
                        this.solutions.add(0, new ArrayList<>(solution));
                        this.maxPoints = temPoints;
                    } else if (temPoints > this.maxPoints) {
                        this.solutions.clear();
                        this.solutions.add(0, new ArrayList<>(solution));
                        this.maxPoints = temPoints;
                    } else if (temPoints == this.maxPoints) {
                        this.solutions.add(0, new ArrayList<>(solution));
                    }
                }
            }
            for (int i = j; i < hand.size(); i++) {
                for (Tile origenTile : this.getPossibleOrigenTiles(hand.get(i), board)) {
                    ArrayList<Tile> origenAdjacents = this.getAdjacents(origenTile, board);

                    for (Tile origenAdjacent : origenAdjacents) {
                        Tile temTile = new Tile(hand.get(i).getColor(), hand.get(i).getShape(), origenAdjacent.getRow(), origenAdjacent.getCol(), origenAdjacent.getIndex(), true);
                        solution.add(temTile);
                        board.get(origenAdjacent.getIndex()).setBusy(true);
                        board.get(origenAdjacent.getIndex()).setColor(hand.get(i).getColor());
                        board.get(origenAdjacent.getIndex()).setShape(hand.get(i).getShape());
                        if (solution.size() == 1) {
                            Tile temOrigen = new Tile(origenTile.getColor(), origenTile.getShape(), origenTile.getRow(), origenTile.getCol(), origenTile.getIndex(), true);
                            solveAux(hand, k, board, j + 1, solution, temOrigen);
                        } else {
                            solveAux(hand, k, board, j + 1, solution, origen);
                        }
                        solution.remove(temTile);
                        board.get(origenAdjacent.getIndex()).setBusy(false);
                        board.get(origenAdjacent.getIndex()).setColor("");
                        board.get(origenAdjacent.getIndex()).setShape("");
                    }
                }
                j++;
            }
        }
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    private int filterSolution(ArrayList<Tile> solution, ArrayList<Tile> board, Tile origen) {
        if (!filterSolutionAux(solution, board, origen)) {
            return 0;
        }
        ArrayList<String> colors = new ArrayList<>();
        ArrayList<String> shapes = new ArrayList<>();
        Tile temTile = origen;
        boolean isColor = true;
        boolean isShape = true;
        boolean flag = true;
        int direction = 1;
        int points = 0;

        colors.add(origen.getColor());
        shapes.add(origen.getShape());

        while (true) {
            ArrayList<Tile> temAdjacents = this.getBusyAdjacents(board.get(temTile.getIndex()), board);
//            System.out.println("adjacents: " + java.util.Arrays.toString(temAdjacents.toArray()));
            if (flag) {
                if (temAdjacents.get(0) != null && solution.get(0).toString().equals(temAdjacents.get(0).toString())) {
                    direction = 1;
                } else if (temAdjacents.get(1) != null && solution.get(0).toString().equals(temAdjacents.get(1).toString())) {
                    direction = 0;
                } else if (temAdjacents.get(2) != null && solution.get(0).toString().equals(temAdjacents.get(2).toString())) {
                    direction = 3;
                } else if (temAdjacents.get(3) != null && solution.get(0).toString().equals(temAdjacents.get(3).toString())) {
                    direction = 2;
                }
                flag = false;
            }
            Tile temTile2 = temAdjacents.get(direction);
            if (temTile2 != null) {
                colors.add(temTile2.getColor());
                shapes.add(temTile2.getShape());
                temTile = temTile2;

//                System.out.println(colors);
//                System.out.println(shapes);
            } else {
                break;
            }
        }

//        System.out.println("------test   origin: " + origen.toString());
//        System.out.println(colors);
//        System.out.println(shapes);
//        System.out.println(solution);

        for (int i = 1; i < colors.size(); i++) {
            if (!colors.get(0).equals(colors.get(i))) {
                isColor = false;
                break;
            }
        }

        for (int i = 1; i < shapes.size(); i++) {
            if (!shapes.get(0).equals(shapes.get(i))) {
                isShape = false;
                break;
            }
        }

        if (!isColor && !isShape) {
//            System.out.println("se fue 1\n");
            return 0;
        }


        if (isShape && isColor) {
//            System.out.println("es los dos");
            points = colors.size() + solution.size();
            if (points == 6) {
                points += 6;
            }
        } else {
            if (isShape) {
                for (Tile tile : solution) {
                    if (colors.contains(tile.getColor())) {
//                        System.out.println("se fue 2\n");
                        return 0;
                    }
                }
                points = colors.size() + solution.size();
                if (points == 6) {
                    points += 6;
                }
            }

            if (isColor) {
                for (Tile tile : solution) {
                    if (shapes.contains(tile.getShape())) {
//                        System.out.println("se fue 3\n");
                        return 0;
                    }
                }
                points = shapes.size() + solution.size();
                if (points == 6) {
                    points += 6;
                }
            }
        }

//        System.out.println("------test final   origin: " + origen.toString());
//        System.out.println(colors);
//        System.out.println(shapes);
//        System.out.println("points : " + points + "\n---------\n\n");

        return points;
    }

    private boolean filterSolutionAux(ArrayList<Tile> solution, ArrayList<Tile> board, Tile origen) {
        if (this.getNumBusyAdjacents(board.get(solution.get(0).getIndex()), board) > 2 ||
                (solution.size() > 1 && this.getNumBusyAdjacents(board.get(solution.get(solution.size() - 1).getIndex()), board) > 1)) {
            return false;
        }

        int temRow = origen.getRow();
        int temCol = origen.getCol();
        String temColor = origen.getColor();
        String temShape = origen.getShape();
        boolean isRow = true;
        boolean isCol = true;
        boolean isColor = true;
        boolean isShape = true;
        for (Tile tile : solution) {
            if (tile.getRow() != temRow) {
                isRow = false;
            }
            if (tile.getCol() != temCol) {
                isCol = false;
            }
            if (!tile.getColor().equals(temColor)) {
                isColor = false;
            }
            if (!tile.getShape().equals(temShape)) {
                isShape = false;
            }
        }

        if (solution.size() > 1) {
            if (!isCol) {
                int col = solution.get(0).getCol();
                for (int i = 1; i < solution.size(); i++) {
                    if (solution.get(i).getCol() + 1 != col && solution.get(i).getCol() - 1 != col) {
                        return false;
                    }
                    col = solution.get(i).getCol();
                }
            }
            if (!isRow) {
                int row = solution.get(0).getRow();
                for (int i = 1; i < solution.size(); i++) {
                    if (solution.get(i).getRow() + 1 != row && solution.get(i).getRow() - 1 != row) {
                        return false;
                    }
                    row = solution.get(i).getRow();
                }
            }
        }

        return (isRow || isCol) && (isColor || isShape);
    }

    private ArrayList<Tile> getPossibleOrigenTiles(Tile tile, ArrayList<Tile> board) {
        ArrayList<Tile> temPossibleOrigenTiles = new ArrayList<>();
        for (Tile possibleTile : board) {
            if (possibleTile.isBusy() &&
                    (possibleTile.getShape().equals(tile.getShape()) || possibleTile.getColor().equals(tile.getColor())) &&
                    !(possibleTile.getShape().equals(tile.getShape()) && possibleTile.getColor().equals(tile.getColor()))) {
                temPossibleOrigenTiles.add(possibleTile);
            }
        }
        return temPossibleOrigenTiles;
    }

    private ArrayList<Tile> getAdjacents(Tile tile, ArrayList<Tile> board) {
        ArrayList<Tile> temAdjacents = new ArrayList<>();

        if (tile.getCol() - 1 >= 0 && !board.get(tile.getIndex() - 1).isBusy()) {
            temAdjacents.add(board.get(tile.getIndex() - 1));
        }
        if (tile.getCol() + 1 < 16 && !board.get(tile.getIndex() + 1).isBusy()) {
            temAdjacents.add(board.get(tile.getIndex() + 1));
        }
        if (tile.getRow() - 1 >= 0 && !board.get(tile.getIndex() - 16).isBusy()) {
            temAdjacents.add(board.get(tile.getIndex() - 16));
        }
        if (tile.getRow() + 1 < 16 && !board.get(tile.getIndex() + 16).isBusy()) {
            temAdjacents.add(board.get(tile.getIndex() + 16));
        }

        return temAdjacents;
    }


    private ArrayList<Tile> getBusyAdjacents(Tile tile, ArrayList<Tile> board) {
        ArrayList<Tile> temAdjacents = new ArrayList<>();
        if (tile.getCol() - 1 >= 0 && board.get(tile.getIndex() - 1).isBusy()) { // izquierda
            temAdjacents.add(board.get(tile.getIndex() - 1));
        } else {
            temAdjacents.add(null);
        }

        if (tile.getCol() + 1 < 16 && board.get(tile.getIndex() + 1).isBusy()) { // derecha
            temAdjacents.add(board.get(tile.getIndex() + 1));
        } else {
            temAdjacents.add(null);
        }

        if (tile.getRow() - 1 >= 0 && board.get(tile.getIndex() - 16).isBusy()) { // arriba
            temAdjacents.add(board.get(tile.getIndex() - 16));
        } else {
            temAdjacents.add(null);
        }

        if (tile.getRow() + 1 < 16 && board.get(tile.getIndex() + 16).isBusy()) { // abajo
            temAdjacents.add(board.get(tile.getIndex() + 16));
        } else {
            temAdjacents.add(null);
        }

        return temAdjacents;
    }

    private int getNumBusyAdjacents(Tile tile, ArrayList<Tile> board) {
        int count = 0;
        if (tile.getCol() - 1 >= 0 && board.get(tile.getIndex() - 1).isBusy()) {
            count++;
        }
        if (tile.getCol() + 1 < 16 && board.get(tile.getIndex() + 1).isBusy()) {
            count++;
        }
        if (tile.getRow() - 1 >= 0 && board.get(tile.getIndex() - 16).isBusy()) {
            count++;
        }

        if (tile.getRow() + 1 < 16 && board.get(tile.getIndex() + 16).isBusy()) {
            count++;
        }
        return count;
    }
}
