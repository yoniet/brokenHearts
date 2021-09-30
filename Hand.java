import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Hand extends Player {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Card> hand = new ArrayList<>();

    public Hand(String namePlayer) {
        super(namePlayer);
        clearHand();
    }

    // Clear the cards in the hand
    public void clearHand() {
        hand.clear();
    }

    public void takeCard(Card card) {
        hand.add(card);
    }

    public Card drawCard(int index) {
        Card draw = hand.get(index);
        hand.remove(index);
        return draw;
    }

    // Given a suit, check if the hand has that suit
	public  boolean checkSuit(Suit check) {
		boolean flag = false;
		if (check == null)
			return false;
		for (Card c : hand) {
			if (c.getSuit() == check)
				flag = true;
		}
		return flag;
	}

    public boolean checkValue(Value check) {
        boolean flag = false;
        if (check == null)
            return false;
        for (Card c : hand) {
            if (c.getValue() == check)
                flag = true;  
        }
        return flag;
    }

    Suit hasSuit() {
        return hand.get(0).getSuit();
    }

    Value hasValue() {
        return hand.get(0).getValue();
    }

    public void printHand() {
        try {
            System.out.print("\n" + super.getName() + "`s hand (" + hand.size() + " card");
            if (hand.size() > 1)
                System.out.print("s");
            System.out.print("):\n|");
            for (int i = 0; i < hand.size(); i++) {
                System.out.format("%5d|", i);
            }
            System.out.print("\n|");
            for (int i = 0; i < hand.size(); i++) {
                TimeUnit.MILLISECONDS.sleep(250);
                // we can either use printCard() or printCardShort()
                System.out.format("%2s|", hand.get(i).printCardShort());
            }
            System.out.println("\n");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Card performAction() {   
		boolean check = true;
		int i = 0;
		while (check) {
			printHand();
			check = false;
			System.out.print("\nEnter the index of the card you ("+ getName() + ") want to play:\n>>> ");
			while (!sc.hasNextInt()) { 
				sc.next(); 
				System.out.println("\nYou did not a valid integer index! Try again!"); 
				printHand();
				System.out.print("\nthe index of the card you ("+ getName() +") want to play:\n>>> ");
			}
			i = sc.nextInt();
			// check if the number is valid for this hand size
			if (i > hand.size() -1) { 
				System.out.println("Invalid card index! Please enter a valid number!"); 
				check = true; 
			}
		}
		System.out.println();
		return hand.remove(i); 
	}

    public void closeScanner() {
        sc.close();
    }

    public int size() {
        return hand.size();
    }

    public Card get(int i) {
        return hand.get(i);
    }

    public void set(int j, Card temp) {
        hand.set(j, temp);
    }

    public void remove(int index) {
        hand.remove(index);
    }

}
