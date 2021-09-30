
public class Test {
    public static void main(String[] args) {
        Hand p1 = new Hand("Yoni");
        Hand p2 = new Hand("Tomer");
        Hand p3 = new Hand("Idan");
        Hand p4 = new Hand("Shelly");

        Deck deck = new Deck();
        Game a = new Game(deck, p1, p2, p3, p4);

        a.playGame();


    }
}
