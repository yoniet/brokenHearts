class Card implements Comparable<Card> {

    Suit suit;
    Value value;
    String shorthand;

    // All cards MUST be constructed with a suit and value!
    Card(Suit thisSuit, Value thisValue) {
        suit = thisSuit;
        value = thisValue;
        setShorthand();
    }

    // This will get the "shorthand" display for this specific card
    void setShorthand() {
        shorthand = "";
        if(suit.equals(Suit.HEARTS) || suit.equals(Suit.DIAMONDS)) {
            switch (value) {
                case TWO:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m2";
                    break;
                case THREE:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m3";
                    break;
                case FOUR:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m4";
                    break;
                case FIVE:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m5";
                    break;
                case SIX:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m6";
                    break;
                case SEVEN:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m7";
                    break;
                case EIGHT:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m8";
                    break;
                case NINE:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m9";
                    break;
                case TEN:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1m10";
                    break;
                case JACK:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1mJ";
                    break;
                case QUEEN:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1mQ";
                    break;
                case KING:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1mK";
                    break;
                case ACE:
                    shorthand += "\u001b[47m \u001b[31m\u001b[1mA";
                    break;
            }
        } else {

            switch (value) {
                case TWO:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m2";
                break;
                case THREE:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m3";
                break;
                case FOUR:
                shorthand += "\u001b[47m\u001b[30m\u001b[1m4";
                break;
                case FIVE:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m5";
                break;
                case SIX:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m6";
                break;
                case SEVEN:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m7";
                break;
                case EIGHT:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m8";
                break;
                case NINE:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m9";
                break;
                case TEN:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m10";
                break;
                case JACK:
                shorthand += "\u001b[47m \u001b[30m\u001b[1mJ";
                break;
                case QUEEN:
                shorthand += "\u001b[47m \u001b[30m\u001b[1mQ";
                break;
                case KING:
                shorthand += "\u001b[47m \u001b[30m\u001b[1mK";
                break;
                case ACE:
                shorthand += "\u001b[47m \u001b[30m\u001b[1mA";
                break;
            }
        }
        switch (suit) {
            case SPADES:
                shorthand += "\u001b[47m \u001b[30m\u001b[1m\u2660 \u001b[0m";
                break;
            case HEARTS:
                shorthand += "\u001b[47m \u001b[31m\u001b[1m\u2661 \u001b[0m";
                break;
            case DIAMONDS:
                shorthand += "\u001b[47m \u001b[31m\u001b[1m\u2662 \u001b[0m";
                break;
            case CLUBS:
                shorthand += " \u001b[47m \u001b[30m\u001b[1m\u2663 \u001b[0m";
                break;
        }
    }

    // Getter functions for the suit, value, and identity of this card
    public Suit getSuit() {
        return suit;
    }

    public Value getValue() {
        return value;
    }

    public String printCard() {
        return getValue() + " of " + getSuit();
    }

    public String printCardShort() {
        return shorthand;
    }

    // Overridden method for comparing cards by suit (for sorting hands)
    public int compareTo(Card other) {
        if (suit.compareTo(other.getSuit()) == 0)
            return value.compareTo(other.getValue());
        return suit.compareTo(other.getSuit());
    }

    // Overridden method to check if cards are the same
    public boolean equals(Card other) {
        return suit.equals(other.getSuit()) && value.equals(other.getValue());
    }

    // Allow a copy function for cards
    public Card copy() {
        return new Card(suit, value);
    }

}