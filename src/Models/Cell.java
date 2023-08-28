package Models;

public class Cell {
    private int row;
    private int column;
    private CellStatus cellStatus;
    private Player player;

    public Cell(int row, int col) {
        this.row = row;
        this.column = col;
        this.cellStatus = CellStatus.empty;
    }

    public Cell(int r, int c, Player player) {
        this(r,c);
        this.player = player;
        this.cellStatus = CellStatus.occupied;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public CellStatus getCellStatus() {
        return cellStatus;
    }

    public void setCellStatus(CellStatus cellStatus) {
        this.cellStatus = cellStatus;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }


}
