package Models;

import Strategy.BotPlayingStrategy;
import Strategy.EasyBotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;

    private BotPlayingStrategy botPlayingStrategy;

    public Bot(String botName, String botSymbol, BotDifficultyLevel difficultyLevel) {
        super(botName,botSymbol,PlayerType.bot);
        this.botDifficultyLevel = difficultyLevel;

        //TODO : factory pattern for the playingStrategy
        if(this.botDifficultyLevel==BotDifficultyLevel.easy){
            botPlayingStrategy = new EasyBotPlayingStrategy();
        }

    }

    public BotDifficultyLevel getBotDifficultyLevel() {
        return botDifficultyLevel;
    }

    public void setBotDifficultyLevel(BotDifficultyLevel botDifficultyLevel) {
        this.botDifficultyLevel = botDifficultyLevel;
    }

    @Override
    public Move decideMove(Board board) {
        return botPlayingStrategy.decideMove(this,board);
    }
}
