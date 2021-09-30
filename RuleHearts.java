import java.util.ArrayList;


public class RuleHearts implements RuleGame {
    private final Card TWO_CLUBS = new Card(Suit.CLUBS, Value.TWO);
    private final String ANSI_CLS = "\u001b[2J";
    private final String ANSI_HOME = "\u001b[H";
    private Deck cardsPlayed;
    private ArrayList<Hand> playerOrder;
    private ArrayList<Scores> scores;
    private Round currentRound;
    private boolean twoClubsPlayed;
    private boolean hasHeartsBroken;
    private int firstPlayer;

    public RuleHearts(ArrayList<Hand> playerOrder, Deck cardsPlayed) {
        this.playerOrder = playerOrder;
        this.cardsPlayed = cardsPlayed;
        this.scores = new ArrayList<>();
        currentRound = new Round();
        twoClubsPlayed = false;
        hasHeartsBroken = false;
        firstPlayer = 0;
    }
    
    public int getRound() {
        return currentRound.getRound();
    }

    public int getRoundsGame() {
        return currentRound.getRoundsGame();
    }

    @Override
    public void handingCards() {
        for (int i = 0; i < 13; i++) {
            for (Hand p : playerOrder) {
                p.takeCard(cardsPlayed.drawTop());
            }
        }
    }

    @Override
    public void sortHands() {
        for (Hand hand : playerOrder) {

            for (int i = 0; i < hand.size(); i++) {
                for (int j = i + 1; j < hand.size(); j++) {
                    if (hand.get(i).getSuit().ordinal() > hand.get(j).getSuit().ordinal()) {
                        swap(hand, i, j);
                    }
                }
            }

            for (int i = 0; i < hand.size(); i++) {
                for (int j = i + 1; j < hand.size(); j++) {
                    if (hand.get(i).getSuit().ordinal() == hand.get(j).getSuit().ordinal()) {
                        if (hand.get(i).getValue().ordinal() > hand.get(j).getValue().ordinal()) {
                            swap(hand, i, j);
                        }
                    }
                }
            }
        }
    }

    private void swap(Hand hand, int i, int j) {
        if (i != j) {
            Card temp = hand.get(i);
            hand.set(i, hand.get(j));
            hand.set(j, temp);
        }
    }

    // Pick first player the one that holds the two of clubs
    public int firstPlayer() {
        for (int i = 0; i < 4; i++) {
            if ((playerOrder.get(i).hasSuit() == Suit.CLUBS) && (playerOrder.get(i).hasValue() == Value.TWO)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public boolean hasAllSameSuit(int player) {
        if (playerOrder.get(player).checkSuit(Suit.HEARTS)) {
            return true;
        }
        return false;
    }

    public Suit getFirstSuit(ArrayList<Card> currentRound) {
        if (currentRound.size() == 0)
            return null;
        return currentRound.get(0).getSuit();
    }

    @Override
    public void initGame() {
        cardsPlayed.shuffleDeck();
        for (Hand hand : playerOrder) {
            hand.clearHand();
        }
        // This optionality Note: don't use this
        // for (Hand p : playerOrder) {
        //     p.clearScore();
        // }
        currentRound.clear();
        scores.clear();
        scores.add(new Scores(0));
        scores.add(new Scores(0));
        scores.add(new Scores(0));
        scores.add(new Scores(0));
        handingCards();
        sortHands();
        for (Hand p : playerOrder) {
            p.printHand();
        }
        System.out.println(ANSI_CLS + ANSI_HOME);
    }

    @Override
    public Scores getScores(int player) {
        return scores.get(player);
    }

    @Override
    public void setScore(int player, int score) {
        scores.get(player).setScore(score);
    }

    @Override
    public void setCurrentRound(Card card) {
        currentRound.setCurrentRound(card);
    }

    // This function moves to the next round
    @Override
    public void nextRound() {
        if (currentRound.validGame()) {
            currentRound.nextRound();
        }
    }

    // This function updates the number of Rounds games
    public void setNumberRounds(int numberOfRounds) {
        currentRound.setRoundsGame(numberOfRounds);
    }

    // This function is for calculating the cards played in the current round
    public int calculatePoints() {
        int points = 0;
        for (int i = 0; i < currentRound.size(); i++) {
            if (currentRound.getCard(i).getSuit() == Suit.HEARTS) {
                points++;
            }
            if (currentRound.getCard(i).getValue() == Value.QUEEN && currentRound.getCard(i).getSuit() == Suit.SPADES) {
                points += 13;
            }
        }
        return points;
    }

    public int findPlayer(int firstPlayer) {
        Suit firstSuit = currentRound.getCard(0).getSuit();
        Value largestValue = currentRound.getCard(0).getValue();
        int taker = firstPlayer;

        for (int i = 0; i < playerOrder.size(); i++) {
            int index = (firstPlayer + i) % playerOrder.size();
            if (currentRound.getCard(i).getSuit() == firstSuit) {
                if (largestValue.compareTo(currentRound.getCard(i).getValue()) < 0) {
                    taker = index;
                    largestValue = currentRound.getCard(i).getValue();
                }
            }
        }
        return taker % playerOrder.size();
    }

    public boolean checkRound(Card playedCard, int index) {
        // That variable is contains Two of Clubs card.
        Card twoClubs = new Card(Suit.CLUBS, Value.TWO);
        // For the check, if the player plays that card.
        if (!twoClubsPlayed && !playedCard.equals(twoClubs)) {
            System.out.println("\nYou need to play Card from kind of Two of Clubs to start the Game");
            return false;
        }
        /**
         * If playing Hearts first, check if Hearts have been broken otherwise, just
         * return true.
         */
        if (currentRound.size() == 0) {
            // If it is true, they can play anything if Hearts has broken.
            if (!hasHeartsBroken && playedCard.getSuit() == Suit.HEARTS) {
                System.out.println("Hearts has not broken yet. You can't play a Heart suit.");
                return false;
            }
            return true;
        }
        // That variable contains a leader of Suit that first play in the round current.
        Suit firstSuit = currentRound.getCard(0).getSuit();
        //
        if (playerOrder.get(index).checkSuit(firstSuit) && playedCard.getSuit() != firstSuit) {
            System.out.println("You still have a card that is " + firstSuit + ". You need to play that first.");
            return false;
        }
        // If the Card played is Hearts, then hearts have broken
        if (playedCard.getSuit() == Suit.HEARTS && !hasHeartsBroken) {
            System.out.println("Hearts has been broken!!!");
            hasHeartsBroken = true;
        }
        return true;
    }

    public void playNewGame() {

        for (int i = 0; i < currentRound.getRoundsGame(); i++) {
            if (currentRound.validGame()) {
                printRound(i);
                // At each round we will clear the table
                currentRound.clear();
                if (!twoClubsPlayed) {
                    firstPlayer = firstPlayer();
                }

                for (int j = 0; j < playerOrder.size(); j++) {
                    // use index to determine the index of the player currently playing
                    int index = (j + firstPlayer) % playerOrder.size();
                    // printRound(firstPlayer); // for debugging: print the cards that were played
                    // this round
                    if (j == 0)
                        hasAllSameSuit(index); // if this is the first player this round, check if only hearts
                    boolean validPlay = false;
                    Card playedCard = null;

                    while (!validPlay) {
                        // This variable is call function performs an action to play card
                        playedCard = playerOrder.get(index).performAction();
                        // Check if the playedCard is valid in current round.
                        validPlay = checkRound(playedCard, index);
                        if (!validPlay) {
                            System.out.println("This was an invalid play.");
                            playerOrder.get(index).takeCard(playedCard);
                            sortHands();
                        }
                    }
                    // Check if played card two of clubs.
                    if (currentRound.getRound() == 0) {
                        if (playedCard.equals(TWO_CLUBS)) {
                            twoClubsPlayed = true;
                        }
                    }

                    System.out.println(playerOrder.get(index).getName() + " played " + playedCard.printCard());
                    // Adding play card to Current Round, and restock Deck Cards
                    currentRound.add(playedCard);
                    cardsPlayed.restock(playedCard);
                    endRound();

                }
            } else {
                System.out.println("Game over");
                printWinner();
            }
            if(checkFinishGame()) {
                break;
            }
            clearScreen();
            currentRound.printRound(playerOrder, firstPlayer);
            printSummery(i);
            // This variable will update who took the card this round
            firstPlayer = findPlayer(firstPlayer);
            int points = calculatePoints();
            scores.get(firstPlayer).addScore(points);
            playerOrder.get(firstPlayer).addScore(points);

            currentRound.clear();
            System.out.println("\n" + playerOrder.get(firstPlayer).getName() + " played the highest card " + "and took "
                    + points + " points this round ");
            Game.printScores(playerOrder);    
        }
        currentRound.nextRound();
    }

    private void printRound(int i) {
        System.out.println("------------------------------");
        System.out.println("Round # " + (i + 1) + ":");
        System.out.println("------------------------------");
    }

    private void printSummery(int i) {
        System.out.println("*********************************");
        System.out.println("Round # " + (i + 1) + " Summary");
        System.out.println("*********************************");
    }

    private void clearScreen() {
        System.out.print(ANSI_CLS + ANSI_HOME);
        System.out.println();
        System.out.flush();
    }

    public void printWinner() {
        int smallestScore = playerOrder.get(0).getScore();
        int index = 0;
        for (int i = 0; i < playerOrder.size(); i++) {
            if (smallestScore > playerOrder.get(i).getScore()) {
                index = i;
                smallestScore = playerOrder.get(i).getScore();
            }
        }
        System.out.println(playerOrder.get(index).getName() + " is in the lead after this round.\n");
    }

    public boolean checkFinishGame() {
        for (int i = 0; i < playerOrder.size(); i++) {
            if(playerOrder.get(i).getScore() > 100) {
                return true;
            }
        }
        return false;
    }

    private void endRound() {
        clearScreen();
        currentRound.printRound(playerOrder, firstPlayer);
    }

}