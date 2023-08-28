package Strategy;

import Models.*;

public interface GameWinningStrategy {
    boolean checkWinner(Board board, Move move);
    void undoMove(Board board,Move move);
}
