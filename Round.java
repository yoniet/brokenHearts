/**
 * 
 */

import java.util.ArrayList;

public class Round {

    // That variable holding all played cards in the current round
    private ArrayList<Card> currentRound;
    // That variable is the sum of rounds for a game
    private int endGame;
    // This variable is counting the number of rounds
    private int round;

    public Round() {
        currentRound = new ArrayList<>();
        endGame = 0;
        round = 0;
    }

    // Holding the cards of the same round.
    // If in another game you take cards off the table
    public void setCurrentRound(Card card) {
        this.currentRound.add(card);
    }

    // Clear the Round for new Round
    public void clear() {
        currentRound.clear();
    }

    public void add(Card playedCard) {
        currentRound.add(playedCard);
    }

    // Take a card of a player who threw to the table in the current round.
    public Card getCard(int index) {
        return currentRound.get(index);
    }

    // Get the number of the current round.
    public int getRound() {
        return round;
    }

    // Promoter for a next round
    public void nextRound() {
        round++;
    }

    // Contains the number of rounds of a games
    public int getRoundsGame() {
        return endGame;
    }

    // Gets the number of rounds of a card game
    public void setRoundsGame(int numberOfRounds) {
        endGame = numberOfRounds;
    }

    // Checks to see if the next round is valid or run out of rounds
    public boolean validGame() {
        return getRoundsGame() > getRound();
    }

    // Checks if this round does not have 4 cards played
    public boolean validRound() {
        return currentRound.size() < 4;
    }

    public int size() {
        return currentRound.size();
    }

    public void printRound(ArrayList<Hand> playerOrder ,int firstPlayer) {
        if(currentRound.size() != 0) {
            System.out.println("\nCards played this round:");
            System.out.println("------------------------");
        }
        if (currentRound.size() == 0) {
            System.out.println("No cards have been played this round.");
        }
        for (int i = 0; i < currentRound.size(); i++) {
            int index = (i + firstPlayer) % playerOrder.size();
            // be careful with the format length -- potentially check for longest name
            // length
            System.out.format("%15s", playerOrder.get(index).getName());
            System.out.print(" played ");
            System.out.format("%3s\n", currentRound.get(i).printCardShort());
        }
        System.out.println();
    }

}
