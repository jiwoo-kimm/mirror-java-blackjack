package blackjack.domain.fixture;

import blackjack.domain.card.Card;
import blackjack.domain.card.Suit;

public class TestCard {

    public static final Card ACE_1 = Card.of(Suit.CLUB, "A");
    public static final Card ACE_2 = Card.of(Suit.DIAMOND, "A");
    public static final Card ACE_3 = Card.of(Suit.HEART, "A");
    public static final Card ACE_4 = Card.of(Suit.SPADE, "A");

    public static final Card CARD_2 = Card.of(Suit.SPADE, 2);
    public static final Card CARD_3 = Card.of(Suit.SPADE, 3);
    public static final Card CARD_4 = Card.of(Suit.SPADE, 4);
    public static final Card CARD_5 = Card.of(Suit.SPADE, 5);
    public static final Card CARD_6 = Card.of(Suit.SPADE, 6);
    public static final Card CARD_7 = Card.of(Suit.SPADE, 7);
    public static final Card CARD_8 = Card.of(Suit.SPADE, 8);
    public static final Card CARD_9 = Card.of(Suit.SPADE, 9);
    public static final Card CARD_10 = Card.of(Suit.SPADE, 10);
    public static final Card CARD_K = Card.of(Suit.SPADE, "K");
    public static final Card CARD_J = Card.of(Suit.SPADE, "J");
    public static final Card CARD_Q = Card.of(Suit.SPADE, "Q");
}
