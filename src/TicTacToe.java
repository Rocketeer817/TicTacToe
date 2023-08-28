import Controllers.GameControllers;
import Models.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class TicTacToe {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the dimension of the tic tac toe game");

        int dimension = scanner.nextInt();

        System.out.println("No of players : ");

        int playersCount = scanner.nextInt();

        List<Player> players = new LinkedList<Player>();

        System.out.println("Is there a bot among the players ? y/n");

        String isBotPresent = scanner.next();

        if(isBotPresent.equals("y")){
            playersCount--;

            System.out.println("Enter the name of the Bot : ");
            String botName = scanner.next();

            System.out.println("Enter the Symbol of the Bot : ");
            String botSymbol = scanner.next();

            System.out.println("Enter Bot Difficulty level : Easy-1 Medium-2 Hard-3");
            int difficultyLevel = scanner.nextInt();

            players.add(new Bot(botName,botSymbol, BotDifficultyLevel.easy));

        }

        for(int i=0;i<playersCount;i++){

            System.out.println("Enter the name of the Player : " + (i+1));
            String playerName = scanner.next();

            System.out.println("Enter the Symbol of the Player : " + (i+1));
            String playerSymbol = scanner.next();

            players.add(new Player(playerName,playerSymbol, PlayerType.human));

        }

        GameControllers gameControllers = new GameControllers();

        Game game = gameControllers.createGame(dimension,players);

        gameControllers.displayBoard(game);

        while(game.getGameStatus()==GameStatus.inProgress){
            //TODO :
            //game.setGameStatus(GameStatus.ended);
            //break;
            System.out.println("Current board");
            gameControllers.displayBoard(game);
            System.out.println("Please enter 1 for next move , 2 for undo the last move");
            int operation = scanner.nextInt();
            if(operation==1){
                System.out.println("Next move");
                gameControllers.executeNextMove(game);
            }
            else{
                System.out.println("Undo the last move");
                gameControllers.undo(game);
            }

        }

        if(game.getGameStatus()==GameStatus.draw){
            System.out.println("Game ended with a draw");
        }
        else{
            System.out.println("Game ended with the win of " + gameControllers.getWinner(game));
        }

    }
}