package Controllers;

import java.util.*;

import Models.*;
import Models.Player;

public class GameControllers {

    public Models.Game createGame(int dimension, List<Player> playersList){
        Game game = Game.getBuilder().setDimension(dimension).setPlayers(playersList).build();

        return game;
    }

    public String getWinner(Game game){
        if(game.getWinner()== null){
            System.out.println("winner has not been assigned in the board. Please check the logic again");
            return null;
        }

        return game.getWinner().getUserName();
    }

    public void setGameStatus(Game game,GameStatus gameStatus){
        game.setGameStatus(gameStatus);
    }

    public void displayBoard(Game game){
        game.getBoard().displayBoard();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();

    }

    public void undo(Game game) {
        game.undo();
    }
}
