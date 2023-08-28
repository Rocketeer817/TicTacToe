package Strategy;

import Models.Board;
import Models.Move;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class OrderOneWinningStrategy implements GameWinningStrategy{
    List<HashMap<String,Integer>> symbolCountbyRows = new LinkedList<>();

    List<HashMap<String,Integer>> symbolCountbyColumns = new LinkedList<>();

    HashMap<String,Integer> symbolCountbyDiagonal1 = new HashMap<>();

    HashMap<String,Integer> symbolCountbyDiagonal2 = new HashMap<>();

    public OrderOneWinningStrategy(int dimension){
        for(int row=0;row<dimension;row++){
            symbolCountbyRows.add(new HashMap<>());
        }

        for(int column=0;column<dimension;column++){
            symbolCountbyColumns.add(new HashMap<>());
        }

    }

    @Override
    public boolean checkWinner(Board board, Move move) {

        String symbol = move.getCell().getPlayer().getSymbol();

        int row = move.getCell().getRow();

        int column = move.getCell().getColumn();

        int dimension = board.getSize();

        symbolCountbyRows.get(row).put(symbol,symbolCountbyRows.get(row).getOrDefault(symbol,0)+1);

        symbolCountbyColumns.get(column).put(symbol,symbolCountbyColumns.get(column).getOrDefault(symbol,0)+1);

        if(row==column){
            symbolCountbyDiagonal1.put(symbol,symbolCountbyDiagonal1.getOrDefault(symbol,0)+1);
        }

        if(row+column == dimension-1){
            symbolCountbyDiagonal2.put(symbol,symbolCountbyDiagonal2.getOrDefault(symbol,0)+1);
        }

        if(symbolCountbyRows.get(row).get(symbol)==dimension
        || symbolCountbyColumns.get(column).get(symbol)==dimension
        || symbolCountbyDiagonal1.getOrDefault(symbol,0)==dimension
        || symbolCountbyDiagonal2.getOrDefault(symbol,0)==dimension){
            return true;
        }

        return false;
    }

    @Override
    public void undoMove(Board board, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getColumn();

        String symbol = move.getCell().getPlayer().getSymbol();

        symbolCountbyRows.get(row).put(symbol,symbolCountbyRows.get(row).get(symbol)-1);
        symbolCountbyColumns.get(col).put(symbol,symbolCountbyColumns.get(col).get(symbol)-1);

        if(row+col==board.getSize()-1){
            symbolCountbyDiagonal2.put(symbol,symbolCountbyDiagonal2.get(symbol)-1);
        }

        if(row==col){
            symbolCountbyDiagonal1.put(symbol,symbolCountbyDiagonal1.get(symbol)-1);
        }
    }


}
