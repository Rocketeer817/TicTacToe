package Strategy;

import Models.*;

public class EasyBotPlayingStrategy implements BotPlayingStrategy {

    @Override
    public Move decideMove(Player player, Board board) {
        for(int i=0;i<board.getSize();i++){
            for(int j=0;j< board.getSize();j++){
                if(board.getCells().get(i).get(j).getCellStatus()== CellStatus.empty){
                    return new Move(i,j,player);
                }
            }
        }
        return null;
    }
}
