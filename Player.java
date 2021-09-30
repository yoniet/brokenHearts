/**
 * Player
 */
public class Player {

    private String name;
    private int score;

    public Player(String namePlayer) {
        name = namePlayer;
        score = 0;
    }

    // Return the name of the player
    public String getName() {
        return this.name;
    }

    // Return the amount of points this player has
    public int getScore() {
        return this.score;
    }

    // Add points to this player
    public void addScore(int score) {
        this.score += score;
    }

    // Initializes the player's score
    public void clearScore() {
        score = 0;
    }

}
