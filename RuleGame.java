

public interface RuleGame {

    void handingCards();

    void sortHands();

    void initGame();

    int firstPlayer();

    void setCurrentRound(Card card);

    Scores getScores(int player);

    void setScore(int player, int score);

    void nextRound();

    void setNumberRounds(int numberOfRounds);

    boolean hasAllSameSuit(int player);

    int calculatePoints(); 

    void playNewGame();

    int findPlayer(int firstPlayer);

    void printWinner();

    int getRound();

    int getRoundsGame();

    boolean checkFinishGame();

    
}