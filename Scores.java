
public class Scores {
    
    private int score;
    private int scoreEndGame;

    public Scores(int score) {
        this.score = score;
        this.scoreEndGame = 0;
    }

    private int getScoreEndGame() {
        return this.scoreEndGame;
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int points) {
        score += points;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public boolean validScore() {
        return getScore() < getScoreEndGame();
    }

    @Override
    public String toString() {
        return " " + getScore();
    }


}
