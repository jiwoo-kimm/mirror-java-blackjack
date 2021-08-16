package blackjack.domain.card;

public class Card {
    static final int MAJOR_CARD_RANK = 10;

    private final Suit suit;
    private final int rank;
    private final String signature;

    public Card(Suit suit, int rank, String signature) {
        this.suit = suit;
        this.rank = rank;
        this.signature = signature;
    }

    public Card(Suit suit, int rank) {
        this(suit, rank, String.valueOf(rank));
    }

    public Card(Suit suit, String signature) {
        this(suit, MAJOR_CARD_RANK, signature);
    }

    public boolean majorCard() {
        return rank == MAJOR_CARD_RANK;
    }

    public int getRank() {
        return rank;
    }

    public String getSignature() {
        return signature;
    }

    public Suit getSuit() {
        return suit;
    }
}
