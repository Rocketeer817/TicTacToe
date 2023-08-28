package Models;

import java.util.Scanner;

public class Player{

    private String userName;
    private String symbol;
    private PlayerType playerType;

    public Player(String name, String symbol) {
        this.userName = name;
        this.symbol = symbol;
    }

    public Player(String name, String symbol, PlayerType playerType) {
        this(name,symbol);
        this.playerType=playerType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }


    public Move decideMove(Board board) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the row to make a move in : ");
        int r = scanner.nextInt();

        System.out.println("Enter the col to make a move in : ");
        int c = scanner.nextInt();

        Move move = new Move(r,c,this);

        return move;
    }
}
