package Models;

public enum BotDifficultyLevel {
    easy(1),
    medium(2),
    hard(3);

    private final int value;

    BotDifficultyLevel(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }


}
