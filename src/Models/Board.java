package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<List<Cell>> cells;
    private int size;

    public Board(int dimension) {
        this.size = dimension;
        this.cells = new ArrayList<>();

        for(int row=0;row<size;row++){
            cells.add(new ArrayList<>());
            for(int col=0;col<size;col++){
                cells.get(row).add(new Cell(row,col));
            }
        }
    }

    public List<List<Cell>> getCells() {
        return cells;
    }

    public void setCells(List<List<Cell>> cells) {
        this.cells = cells;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void displayBoard() {

        for(int row = 0;row<size;row++){
            for(int col=0;col<size;col++){
                System.out.print("| ");
                System.out.print(cells.get(row).get(col).getCellStatus()==CellStatus.empty?"" : cells.get(row).get(col).getPlayer().getSymbol());
                System.out.print(" |");
            }
            System.out.println();
        }

    }

    public void makeMove(Move move, int r, int c) {
        Cell cell = this.cells.get(r).get(c);
        cell.setCellStatus(CellStatus.occupied);
        cell.setPlayer(move.getCell().getPlayer());
    }
}
