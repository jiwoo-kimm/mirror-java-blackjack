package blackjack.domain.card;

import blackjack.exception.NoSuchCardException;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Card {

    private static final Map<String, Card> CARD_CACHE = new HashMap<>();

    static {
        Arrays.stream(Suit.values())
                .forEach(suit -> Arrays.stream(Signature.values())
                        .forEach(signature -> CARD_CACHE.put(generateKey(suit, signature.getSymbol()), new Card(suit, signature))));
    }

    private final Suit suit;
    private final Signature signature;

    private Card(Suit suit, Signature signature) {
        this.suit = suit;
        this.signature = signature;
    }

    private static String generateKey(Suit suit, String symbol) {
        return suit.getName() + symbol;
    }

    public static Card of(Suit suit, String symbol) {
        String key = generateKey(suit, symbol);
        Card card = CARD_CACHE.get(key);

        if (card == null) {
            throw new NoSuchCardException(key);
        }

        return card;
    }

    public static Card of(Suit suit, int rank) {
        return of(suit, String.valueOf(rank));
    }

    public static Collection<Card> getAll() {
        return CARD_CACHE.values();
    }

    public boolean isMajorCard() {
        return signature.isMajor();
    }

    public boolean isAceCard() {
        return signature.isAce();
    }

    public boolean isNotAceCard() {
        return !isAceCard();
    }

    public Suit getSuit() {
        return suit;
    }

    public String getSuitName() {
        return suit.getName();
    }

    public String getSymbol() {
        return signature.getSymbol();
    }

    public int getRank() {
        return signature.getRank();
    }
}
