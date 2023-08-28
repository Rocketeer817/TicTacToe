package Models;

import Exceptions.InvalidGameDimensionException;
import Exceptions.InvalidGamePlayersCountException;
import Strategy.GameWinningStrategy;
import Strategy.OrderOneWinningStrategy;

import java.util.LinkedList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> playerList;
    private List<Move> moveList;
    private int nextPlayerIndex;
    private GameStatus gameStatus;
    private Player winner;
    private GameWinningStrategy gameWinningStrategy;


    public static Builder getBuilder() {
        return new Builder();
    }

    //getters and setters
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public Player getWinner() {
        return winner;
    }

    public void makeNextMove() {
        Player currPlayer = playerList.get(nextPlayerIndex);
        System.out.println("Next player to make a move is "+ currPlayer.getUserName());

        Move move = currPlayer.decideMove(board);
        int r = move.getCell().getRow();
        int c = move.getCell().getColumn();

        if(board.getCells().get(r).get(c).getCellStatus()==CellStatus.empty){
            //update the cell status
            board.makeMove(move, r, c);
            //memoize the move
            this.moveList.add(move);

            if(gameWinningStrategy.checkWinner(board,move)){
                gameStatus = GameStatus.ended;
                winner = move.getCell().getPlayer();
            }

            //check for draw
            if(moveList.size()== board.getSize()* board.getSize()){
                gameStatus = GameStatus.draw;
            }

            //move the nextIndex pointer
            this.nextPlayerIndex = (this.nextPlayerIndex+1)%playerList.size();

        }
        else{

        }


    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }

    public void undo() {
        if(moveList.size()==0){
            System.out.println("There are no moves till now");
            return;
        }

        Move lastMove = moveList.get(moveList.size()-1);

        lastMove.undo(board);

        moveList.remove(moveList.size()-1);

        gameWinningStrategy.undoMove(board,lastMove);

        nextPlayerIndex = (nextPlayerIndex-1+ board.getSize())% board.getSize();

    }


    public static class Builder{
        private int dimension;

        private List<Player> players;


        public Builder setDimension(int dimension) {
            this.dimension = dimension;
            return this;
        }

        public Builder setPlayers(List<Player> players) {
            this.players = players;
            return this;
        }

        public Game build(){
            try{
                validate();
            }
            catch(InvalidGameDimensionException e){
                return null;
            } catch (InvalidGamePlayersCountException e) {
                return null;
            }

            Game game = new Game();
            game.setBoard(new Board(dimension));

            game.setPlayerList(players);

            game.setMoveList(new LinkedList<>());

            game.setGameStatus(GameStatus.inProgress);

            game.setNextPlayerIndex(0);

            game.setGameWinningStrategy( new OrderOneWinningStrategy(dimension) );

            return game;

        }

        private boolean validate() throws InvalidGameDimensionException, InvalidGamePlayersCountException {
            if(dimension<3){
                throw new InvalidGameDimensionException("dimension should be >=3");
            }

            if(players.size()<2){
                throw new InvalidGamePlayersCountException("no of players should be >=3");
            }


            return true;
        }
    }




}
