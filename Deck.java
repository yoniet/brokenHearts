import java.util.*;
import java.util.concurrent.TimeUnit;

public class Deck {
    // Use this to check if init the Deck once or not
    private boolean initCounter;

    // these are all of the cards currently in the deck
    ArrayList<Card> allCards = new ArrayList<Card>();

    ArrayList<Card> invertDeck = new ArrayList<Card>();

    // Initialize the fifty two cards and shuffle the deck
    public Deck() {
        initCounter = true;
        initDeck();
        shuffleDeck();
    }

    // generate deck for the first time.
    // never be called this method outside constructor
    private void initDeck() {
        if (initCounter) {
            System.out.println("The deck has been initialized.");
            for (Suit suit : Suit.values()) {
                for (Value value : Value.values()) {
                    allCards.add(new Card(suit, value));
                }
            }
            initCounter = false;
        }
    }

    // this is used to shuffle the deck
    // This method uses the seed for a given time on the computer for shuffle random
    public void shuffleDeck() {
        long seed = System.nanoTime();
        Collections.shuffle(allCards, new Random(seed));
        printDeck();
    }

    public void restock(Card returned) {
        allCards.add(returned);
        invertDeck.remove(returned);
    }

    private void printDeck() {
        for (Card card : allCards) {
            // System.out.print(card.printCard() + ", ");
            try {
                TimeUnit.MILLISECONDS.sleep(200);
                System.out.print(card.printCardShort() + ", ");
                System.out.print("\u001b[0m");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("\n\nSize of Deck: " + allCards.size() + "\n");
    }

    public Card drawTop() {
        if (allCards.size() != 0) {
            // as you deal from the deck, add a pointer to the invertDeck
            invertDeck.add(allCards.get(allCards.size() - 1));
            return allCards.remove(allCards.size() - 1);
        } else
            System.out.println("The Deck is empty, cannot draw from it!");
        return null;
    }

    // Return the number of cards in the Deck
    public int size() {
        return allCards.size();
    }

    // check that we have all 52 unique cards
    boolean checkDeck() {
        if (allCards.size() == 52 && invertDeck.size() == 0)
            return true;
        return false;
    }

}
