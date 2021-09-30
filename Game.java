import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Game {

    private final String ANSI_CLS = "\u001b[2J";
    private final String ANSI_HOME = "\u001b[H";

    Scanner sc = new Scanner(System.in);

    private Deck cardsPlayed;
    private ArrayList<Hand> playerOrder;
    private int firstPlayer;

    private RuleGame rule;

    Game(Deck deck, Hand p1, Hand p2, Hand p3, Hand p4) {
        playerOrder = new ArrayList<Hand>();
        playerOrder.add(p1);
        playerOrder.add(p2);
        playerOrder.add(p3);
        playerOrder.add(p4);
        cardsPlayed = deck;
        // playerScores = new ArrayList<>(4);
        firstPlayer = 0;
    }

    public void playGame() {
        int numGame = 0;
        do {
            System.out.println("Hearts game is number 1\n" + "Soliter Game is number 2");
            System.out.print("Enter number of a game you want to play: ");
            numGame = sc.nextInt();
        } while (numGame == 0 || numGame > 2);
        if (numGame == 1) {
            rule = new RuleHearts(playerOrder, cardsPlayed);

            // That call is entering the number of rounds for a game
            rule.setNumberRounds(13);

            while (rule.getRound() < rule.getRoundsGame() && !rule.checkFinishGame()) {
                // rule.handingCards();
                rule.initGame();
                firstPlayer = rule.firstPlayer();
                System.out.println(ANSI_CLS + ANSI_HOME);
                System.out.println("\u001b[37m**********************************************\n");
                String t = "has the two of clubs and will play first";
                System.out.print("\u001b[1m\u001b[32m" + playerOrder.get(firstPlayer).getName() + " ");
                char[] c = t.toCharArray();
                for (char d : c) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        System.out.print("\u001b[1m\u001b[32m" + d);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                System.out.println(".\n\n\u001b[37m**********************************************\n\u001b[0m");
                rule.playNewGame();
            }
            rule.printWinner();
        } else {
            // System.out.print(ANSI_CLS + ANSI_HOME);
            System.out.println("\nThere is currently no game available\n");
        }
    }

    public static void printScores(ArrayList<Hand> player) {
        System.out.println();
        System.out.println("\n\u001b[34mTable scores in this round: \u001b[0m");
        System.out.println("\u001b[33m-------------------------- \u001b[0m");
        for (int i = 0; i < player.size(); i++) {
            System.out.println(player.get(i).getName() + " has " + player.get(i).getScore() + " points.");
        }
        System.out.println();
    }
}
