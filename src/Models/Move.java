package Models;

public class Move {
    private Cell cell;

    public Move(int r, int c, Player player) {
        this.cell = new Cell(r,c,player);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void undo(Board board) {
        int row = this.getCell().getRow();
        int col = this.getCell().getColumn();
        System.out.println("Undoing the move of "+ this.getCell().getPlayer().getUserName() + " at "+row+","+col);

        //undo the move in board
        Cell cell = board.getCells().get(row).get(col);
        cell.setCellStatus(CellStatus.empty);
        cell.setPlayer(null);

    }


}
